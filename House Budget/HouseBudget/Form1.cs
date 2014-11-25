using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using System.Xml;
using System.IO;
using System.Collections;

namespace HouseBudget
{



    public partial class Form1 : Form
    {
        XmlDocument doc;
        List<Party> parties;
        Party prty;
        double[,] owes;
        int sumOfParties;
        public Form1()
        {
            InitializeComponent();
        }

        private void exitToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            doc = new XmlDocument();
            doc.Load("houseData.dat");
            parties = new List<Party>();
            prty = new Party();
            XmlNodeList nodeList = doc.SelectNodes("root/parties/party");
            foreach (XmlNode node in nodeList)
            {
                parties.Add(new Party(node.Attributes["name"].Value, int.Parse(node.Attributes["count"].Value)));
            }
            parties.Sort((s1, s2) => s1.Name.CompareTo(s2.Name));
            sumOfParties = 0;
            foreach (Party p in parties)
            {
                sumOfParties += p.Count;
            }

            refreshNameLists();
            
            LoadMonths();
            cbCategory.SelectedIndex = 0;
        }

        private void refreshNameLists()
        {
            cbPaidBy.Items.Clear();
            cbPaidFrom.Items.Clear();
            cbPaidTo.Items.Clear();
            lbParties.Items.Clear();

            foreach (Party s in parties)
            {
                cbPaidBy.Items.Add(s.Name);
                cbPaidFrom.Items.Add(s.Name);
                cbPaidTo.Items.Add(s.Name);
                lbParties.Items.Add(s.ToString());
            }
        }

        private void LoadMonths()
        {
            owes = new double[parties.Count, parties.Count];
            Party pPaidBy = new Party();
            Party pPaidTo = new Party();

            lblOwes.Text = "";
            tcMoney.TabPages.Clear();
            XmlNode months = doc.SelectSingleNode("root/months");
            foreach (XmlNode node in months.ChildNodes)
            {
                
                
                List<Trans> transList = new List<Trans>();
                foreach (XmlNode childNode in node.ChildNodes)
                {
                    transList.Add(new Trans(childNode.Name,childNode.Attributes["date"].Value,childNode.Attributes["amount"].Value,
                        childNode.Attributes["paidBy"].Value,childNode.Attributes["paidTo"]==null?"":childNode.Attributes["paidTo"].Value,
                        childNode.Attributes["desc"] == null ? "" : childNode.Attributes["desc"].Value, 
                        childNode.Attributes["cat"] == null ? "" : childNode.Attributes["cat"].Value));
                }
                transList.Sort();

                DataGridView newView = addMonth(node.Name);

                foreach (Trans tr in transList)
                {
                    if (tr.DateEntered == null)
                        tr.DateEntered = tr.Date;
                    string[] newRow = { tr.Type.ToUpper(), tr.Date, tr.PaidBy, tr.PaidTo, tr.Amount, tr.Category, tr.Description };
                    newView.Rows.Add(newRow);
                    foreach( Party p in parties)
                    {
                        if (p.Name == tr.PaidBy){
                            pPaidBy = p;
                        }
                        if (p.Name == tr.PaidTo){
                            pPaidTo = p;
                        }
                    }
                    if (tr.Type == "purchase")
                    {
                        double amt = Double.Parse(tr.Amount) / sumOfParties;
                        for (int i = 0; i < parties.Count; i++)
                        {
                            if (i != parties.IndexOf(pPaidBy))
                                owes[parties.IndexOf(pPaidBy), i] += amt * parties[i].Count;
                        }
                    }
                    else
                    {
                        double amt = Double.Parse(tr.Amount);
                        owes[parties.IndexOf(pPaidTo), parties.IndexOf(pPaidBy)] -= amt;
                    }
                }
                recalculateTotals(node.Name);
                
            }

            //select the correct tab
            string currentMonthName = purchaseDate.Value.ToString("MMMyyyy");
            tcMoney.SelectedTab = tcMoney.TabPages[currentMonthName];


            //display total who owes who what
            for (int i = 0; i < parties.Count; i++)
            {
                for (int j = 0; j < parties.Count; j++)
                {
                    if (i != j)
                    {
                        double amtOwed = owes[j, i] - owes[i, j];
                        if (amtOwed > 0)
                        {
                            lblOwes.Text += parties[i].Name + " owes " + parties[j].Name + " " + String.Format("{0:C}", amtOwed) + "\n";
                        }
                    }
                }
            }
        }

        private void recalculateTotals(string monthName)
        {
            DataGridView currentMonth = (DataGridView)tcMoney.TabPages[monthName].Controls["dgv" + monthName];
            Label lblTotals = (Label)tcMoney.TabPages[monthName].Controls["tot" + monthName];
            double totalPurchase = 0.0, totalFood = 0.0, totalUtil = 0.0, totalMisc = 0.0;
            foreach (DataGridViewRow row in currentMonth.Rows)
            {
                if (row.Cells["Type"].Value != null)
                {
                    if (row.Cells["Type"].Value.ToString() == "PURCHASE")
                    {
                        totalPurchase += Double.Parse(row.Cells["Amount"].Value.ToString());
                        switch (row.Cells["Category"].Value.ToString())
                        {
                            case "Food":
                                totalFood += Double.Parse(row.Cells["Amount"].Value.ToString());
                                break;
                            case "Utilities":
                                totalUtil += Double.Parse(row.Cells["Amount"].Value.ToString());
                                break;
                            case "Misc":
                                totalMisc += Double.Parse(row.Cells["Amount"].Value.ToString());
                                break;
                        }
                    }
                }
            }
            lblTotals.Width = 450;
            lblTotals.Text = String.Format("Purchases: {0:c}   Food: {1:c}   Utilities: {2:c} Misc: {3:c}", totalPurchase, totalFood, totalUtil, totalMisc);

        }

        private DataGridView addMonth(string monthName)
        {
            tcMoney.TabPages.Add(monthName, monthName);

            DataGridView newView = new DataGridView();
            
            newView.ColumnCount = 7;

            newView.ColumnHeadersDefaultCellStyle.BackColor = Color.Navy;
            newView.ColumnHeadersDefaultCellStyle.ForeColor = Color.White;
            newView.ColumnHeadersDefaultCellStyle.Font =
                new Font(newView.Font, FontStyle.Bold);

            newView.Name = "dgv" + monthName;
            newView.AutoSizeRowsMode = DataGridViewAutoSizeRowsMode.DisplayedCellsExceptHeaders;
            newView.ColumnHeadersBorderStyle = DataGridViewHeaderBorderStyle.Raised;
            newView.CellBorderStyle = DataGridViewCellBorderStyle.Single;
            newView.GridColor = Color.Black;
            newView.RowHeadersVisible = true;

            newView.Columns[0].Name = "Type";
            newView.Columns[1].Name = "Date";
            newView.Columns[2].Name = "Paid By";
            newView.Columns[3].Name = "Paid To";
            newView.Columns[4].Name = "Amount";
            newView.Columns[5].Name = "Category";
            newView.Columns[6].Name = "Description";

            newView.SelectionMode = DataGridViewSelectionMode.FullRowSelect;
            newView.MultiSelect = false;
            newView.Dock = DockStyle.Fill;

            Label totalLabel = new Label();
            totalLabel.Name = "tot" + monthName;
            totalLabel.Text = "Total: ";
            tcMoney.TabPages[monthName].Controls.Add(totalLabel);

            tcMoney.TabPages[monthName].Controls.Add(newView);
            newView.Anchor = AnchorStyles.Top | AnchorStyles.Left | AnchorStyles.Bottom | AnchorStyles.Right;
            newView.Width = newView.Parent.Width;
            newView.Height = newView.Parent.Height - 25;
            newView.Top = 25;
            return newView;
        }

        private void btnAddResident_Click(object sender, EventArgs e)
        {
            XmlNode residentsNode = doc.SelectSingleNode("root/parties");
            XmlElement newNode = doc.CreateElement("party");
            XmlAttribute nameAtt = doc.CreateAttribute("name");
            XmlAttribute countAtt = doc.CreateAttribute("count");
            nameAtt.Value = txtName.Text;
            countAtt.Value = txtCount.Text;
            newNode.Attributes.Append(nameAtt);
            newNode.Attributes.Append(countAtt);
            residentsNode.AppendChild(newNode);
            doc.Save("houseData.dat");
            sumOfParties += int.Parse(txtCount.Text);
            parties.Add(new Party(txtName.Text, int.Parse(txtCount.Text)));
            parties.Sort((s1, s2) => s1.Name.CompareTo(s2.Name));
            refreshNameLists();
            LoadMonths();
        }

        private void backupToolStripMenuItem_Click(object sender, EventArgs e)
        {
            File.Copy("houseData.dat", "houseData_" + DateTime.Now.ToString("MMddyyyyHHmmss") + ".bkp");
            MessageBox.Show("Data backup complete.");
        }

        private void btnClearReceipt_Click(object sender, EventArgs e)
        {
            cbPaidBy.SelectedIndex = -1;
            cbCategory.SelectedIndex = 0;
            txtDescription.Clear();
            txtAmountPurchased.Clear();
        }

        private void btnAddPurchase_Click(object sender, EventArgs e)
        {
            string errs = "";
            if (cbPaidBy.SelectedItem == null)
                errs += "You must select a 'Paid for By' value.\n";
            if (txtDescription.Text == "")
                errs += "You must enter a description.\n";
            if (txtAmountPurchased.Text == "")
                errs += "You must enter an amount.\n";
            if (errs != "")
            {
                MessageBox.Show(errs, "Error");
                return;
            }
                

            XmlNode monthsNode = doc.SelectSingleNode("root/months");
            if (monthsNode == null)
            {
                monthsNode = doc.CreateElement("months");
                doc.SelectSingleNode("root").AppendChild(monthsNode);
            }
            string currentMonthName = purchaseDate.Value.ToString("MMMyyyy");
            XmlNode currentMonth = monthsNode.SelectSingleNode(currentMonthName);
            if (currentMonth == null)
            {
                currentMonth = doc.CreateElement(currentMonthName);
                monthsNode.AppendChild(currentMonth);
            }
            Trans tr = new Trans("purchase",purchaseDate.Value.ToString("MM/dd/yyyy"),txtAmountPurchased.Text,cbPaidBy.SelectedItem.ToString(),null,txtDescription.Text,cbCategory.SelectedItem.ToString());

            //create the purchase
            XmlElement purchase = doc.CreateElement(tr.Type);
            XmlAttribute newAtt = doc.CreateAttribute("paidBy");
            newAtt.Value = tr.PaidBy;
            purchase.Attributes.Append(newAtt);
            newAtt = doc.CreateAttribute("date");
            newAtt.Value = tr.Date;
            purchase.Attributes.Append(newAtt);
            newAtt = doc.CreateAttribute("desc");
            newAtt.Value = tr.Description;
            purchase.Attributes.Append(newAtt);
            newAtt = doc.CreateAttribute("amount");
            newAtt.Value = tr.Amount;;
            purchase.Attributes.Append(newAtt);
            newAtt = doc.CreateAttribute("cat");
            newAtt.Value = tr.Category; ;
            purchase.Attributes.Append(newAtt);
            newAtt = doc.CreateAttribute("enteredDate");
            newAtt.Value = tr.DateEntered;
            purchase.Attributes.Append(newAtt);
            currentMonth.AppendChild(purchase);

            string[] newRow = { tr.Type.ToUpper(), tr.Date, tr.PaidBy, tr.PaidTo, tr.Amount, tr.Description };
            if (tcMoney.TabPages[currentMonthName] == null)
            {
                addMonth(currentMonthName);
            }
            ((DataGridView)tcMoney.TabPages[currentMonthName].Controls["dgv" + currentMonthName]).Rows.Add(newRow);

            doc.Save("houseData.dat");
            btnClearReceipt_Click(null, null);
            LoadMonths();
        }

        private void btnClearPayment_Click(object sender, EventArgs e)
        {
            cbPaidFrom.SelectedIndex = -1;
            cbPaidTo.SelectedIndex = -1;
            txtAmountPaid.Clear();
        }

        private void btnAddPayment_Click(object sender, EventArgs e)
        {
            string errs = "";
            if (cbPaidFrom.SelectedItem == null)
                errs += "You must select a 'Paid From' value.\n";
            if (cbPaidTo.SelectedItem == null)
                errs += "You must select a 'Paid To' value.\n";
            if (txtAmountPaid.Text == "")
                errs += "You must enter an amount.\n";
            if (errs != "")
            {
                MessageBox.Show(errs, "Error");
                return;
            }
            XmlNode monthsNode = doc.SelectSingleNode("root/months");
            if (monthsNode == null)
            {
                monthsNode = doc.CreateElement("months");
                doc.SelectSingleNode("root").AppendChild(monthsNode);
            }
            string currentMonthName = purchaseDate.Value.ToString("MMMyyyy");
            XmlNode currentMonth = monthsNode.SelectSingleNode(currentMonthName);
            if (currentMonth == null)
            {
                currentMonth = doc.CreateElement(currentMonthName);
                monthsNode.AppendChild(currentMonth);
            }
            Trans tr = new Trans("payment", paymentDate.Value.ToString("MM/dd/yyyy"), txtAmountPaid.Text, cbPaidFrom.SelectedItem.ToString(), cbPaidTo.SelectedItem.ToString(), null,null);

            //create the purchase
            XmlElement payment = doc.CreateElement("payment");
            XmlAttribute newAtt = doc.CreateAttribute("paidBy");
            newAtt.Value = tr.PaidBy;
            payment.Attributes.Append(newAtt);
            newAtt = doc.CreateAttribute("paidTo");
            newAtt.Value = tr.PaidTo;
            payment.Attributes.Append(newAtt);
            newAtt = doc.CreateAttribute("date");
            newAtt.Value = tr.Date;
            payment.Attributes.Append(newAtt);
            newAtt = doc.CreateAttribute("amount");
            newAtt.Value = tr.Amount;
            payment.Attributes.Append(newAtt);
            currentMonth.AppendChild(payment);

            string[] newRow = { tr.Type.ToUpper(), tr.Date, tr.PaidBy, tr.PaidTo, tr.Amount, tr.Description };
            if (tcMoney.TabPages[currentMonthName] == null)
            {
                addMonth(currentMonthName);
            }
            ((DataGridView)tcMoney.TabPages[currentMonthName].Controls["dgv" + currentMonthName]).Rows.Add(newRow);

            doc.Save("houseData.dat");
            btnClearPayment_Click(null, null);
            LoadMonths();
        }

        private void numeric_KeyPress(object sender, KeyPressEventArgs e)
        {
            if (!Char.IsDigit(e.KeyChar) && e.KeyChar != '\b' && e.KeyChar != '.' && e.KeyChar != '-')
                e.Handled = true;
            if (e.KeyChar == '.')
            {
                if (((TextBox)sender).Text.Contains("."))
                    e.Handled = true;
            }
        }

        private void delinquentEntriesToolStripMenuItem_Click(object sender, EventArgs e)
        {
            DelinquentEntries de = new DelinquentEntries("houseData.dat");
            de.Show();
        }
    }

    class Party
    {
        private string name;
        private int count;

        public Party()
        {
        }
        public Party(string name, int count)
        {
            this.name = name;
            this.count = count;
        }
        public string Name
        {
            get { return name; }
            set { name = value; }
        }
        public int Count
        {
            get { return count; }
            set { count = value; }
        }
        public override string ToString()
        {
            return name + ", (" + count + ")";
        }

    }
}
