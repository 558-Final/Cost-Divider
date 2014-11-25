namespace HouseBudget
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.menuStrip1 = new System.Windows.Forms.MenuStrip();
            this.exitToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.backupToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.tabControl1 = new System.Windows.Forms.TabControl();
            this.tpReceipt = new System.Windows.Forms.TabPage();
            this.cbCategory = new System.Windows.Forms.ComboBox();
            this.label10 = new System.Windows.Forms.Label();
            this.label7 = new System.Windows.Forms.Label();
            this.txtAmountPurchased = new System.Windows.Forms.TextBox();
            this.btnClearReceipt = new System.Windows.Forms.Button();
            this.btnAddPurchase = new System.Windows.Forms.Button();
            this.label3 = new System.Windows.Forms.Label();
            this.txtDescription = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.cbPaidBy = new System.Windows.Forms.ComboBox();
            this.purchaseDate = new System.Windows.Forms.DateTimePicker();
            this.tpPayment = new System.Windows.Forms.TabPage();
            this.label9 = new System.Windows.Forms.Label();
            this.paymentDate = new System.Windows.Forms.DateTimePicker();
            this.cbPaidTo = new System.Windows.Forms.ComboBox();
            this.btnClearPayment = new System.Windows.Forms.Button();
            this.btnAddPayment = new System.Windows.Forms.Button();
            this.label4 = new System.Windows.Forms.Label();
            this.txtAmountPaid = new System.Windows.Forms.TextBox();
            this.label5 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.cbPaidFrom = new System.Windows.Forms.ComboBox();
            this.tpResident = new System.Windows.Forms.TabPage();
            this.lvResidents = new System.Windows.Forms.ListView();
            this.btnAddResident = new System.Windows.Forms.Button();
            this.txtName = new System.Windows.Forms.TextBox();
            this.label8 = new System.Windows.Forms.Label();
            this.tcMoney = new System.Windows.Forms.TabControl();
            this.tabPage1 = new System.Windows.Forms.TabPage();
            this.tabPage2 = new System.Windows.Forms.TabPage();
            this.lblOwes = new System.Windows.Forms.Label();
            this.menuStrip1.SuspendLayout();
            this.tabControl1.SuspendLayout();
            this.tpReceipt.SuspendLayout();
            this.tpPayment.SuspendLayout();
            this.tpResident.SuspendLayout();
            this.tcMoney.SuspendLayout();
            this.SuspendLayout();
            // 
            // menuStrip1
            // 
            this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.exitToolStripMenuItem,
            this.backupToolStripMenuItem});
            this.menuStrip1.Location = new System.Drawing.Point(0, 0);
            this.menuStrip1.Name = "menuStrip1";
            this.menuStrip1.Size = new System.Drawing.Size(814, 24);
            this.menuStrip1.TabIndex = 0;
            this.menuStrip1.Text = "menuStrip1";
            // 
            // exitToolStripMenuItem
            // 
            this.exitToolStripMenuItem.Name = "exitToolStripMenuItem";
            this.exitToolStripMenuItem.Size = new System.Drawing.Size(37, 20);
            this.exitToolStripMenuItem.Text = "E&xit";
            this.exitToolStripMenuItem.Click += new System.EventHandler(this.exitToolStripMenuItem_Click);
            // 
            // backupToolStripMenuItem
            // 
            this.backupToolStripMenuItem.Name = "backupToolStripMenuItem";
            this.backupToolStripMenuItem.Size = new System.Drawing.Size(58, 20);
            this.backupToolStripMenuItem.Text = "&Backup";
            this.backupToolStripMenuItem.Click += new System.EventHandler(this.backupToolStripMenuItem_Click);
            // 
            // tabControl1
            // 
            this.tabControl1.Controls.Add(this.tpReceipt);
            this.tabControl1.Controls.Add(this.tpPayment);
            this.tabControl1.Controls.Add(this.tpResident);
            this.tabControl1.Location = new System.Drawing.Point(12, 27);
            this.tabControl1.Name = "tabControl1";
            this.tabControl1.SelectedIndex = 0;
            this.tabControl1.Size = new System.Drawing.Size(340, 185);
            this.tabControl1.TabIndex = 1;
            // 
            // tpReceipt
            // 
            this.tpReceipt.Controls.Add(this.cbCategory);
            this.tpReceipt.Controls.Add(this.label10);
            this.tpReceipt.Controls.Add(this.label7);
            this.tpReceipt.Controls.Add(this.txtAmountPurchased);
            this.tpReceipt.Controls.Add(this.btnClearReceipt);
            this.tpReceipt.Controls.Add(this.btnAddPurchase);
            this.tpReceipt.Controls.Add(this.label3);
            this.tpReceipt.Controls.Add(this.txtDescription);
            this.tpReceipt.Controls.Add(this.label2);
            this.tpReceipt.Controls.Add(this.label1);
            this.tpReceipt.Controls.Add(this.cbPaidBy);
            this.tpReceipt.Controls.Add(this.purchaseDate);
            this.tpReceipt.Location = new System.Drawing.Point(4, 22);
            this.tpReceipt.Name = "tpReceipt";
            this.tpReceipt.Padding = new System.Windows.Forms.Padding(3);
            this.tpReceipt.Size = new System.Drawing.Size(332, 159);
            this.tpReceipt.TabIndex = 0;
            this.tpReceipt.Text = "Purchase";
            this.tpReceipt.UseVisualStyleBackColor = true;
            // 
            // cbCategory
            // 
            this.cbCategory.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cbCategory.FormattingEnabled = true;
            this.cbCategory.Items.AddRange(new object[] {
            "Food",
            "Utilities",
            "Misc"});
            this.cbCategory.Location = new System.Drawing.Point(66, 55);
            this.cbCategory.Name = "cbCategory";
            this.cbCategory.Size = new System.Drawing.Size(121, 21);
            this.cbCategory.TabIndex = 11;
            // 
            // label10
            // 
            this.label10.AutoSize = true;
            this.label10.Location = new System.Drawing.Point(6, 56);
            this.label10.Name = "label10";
            this.label10.Size = new System.Drawing.Size(52, 13);
            this.label10.TabIndex = 10;
            this.label10.Text = "Category:";
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(6, 105);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(46, 13);
            this.label7.TabIndex = 9;
            this.label7.Text = "Amount:";
            // 
            // txtAmountPurchased
            // 
            this.txtAmountPurchased.Location = new System.Drawing.Point(67, 102);
            this.txtAmountPurchased.Name = "txtAmountPurchased";
            this.txtAmountPurchased.Size = new System.Drawing.Size(121, 20);
            this.txtAmountPurchased.TabIndex = 4;
            // 
            // btnClearReceipt
            // 
            this.btnClearReceipt.Location = new System.Drawing.Point(92, 131);
            this.btnClearReceipt.Name = "btnClearReceipt";
            this.btnClearReceipt.Size = new System.Drawing.Size(75, 23);
            this.btnClearReceipt.TabIndex = 6;
            this.btnClearReceipt.Text = "Clear";
            this.btnClearReceipt.UseVisualStyleBackColor = true;
            this.btnClearReceipt.Click += new System.EventHandler(this.btnClearReceipt_Click);
            // 
            // btnAddPurchase
            // 
            this.btnAddPurchase.Location = new System.Drawing.Point(9, 131);
            this.btnAddPurchase.Name = "btnAddPurchase";
            this.btnAddPurchase.Size = new System.Drawing.Size(75, 23);
            this.btnAddPurchase.TabIndex = 5;
            this.btnAddPurchase.Text = "Add";
            this.btnAddPurchase.UseVisualStyleBackColor = true;
            this.btnAddPurchase.Click += new System.EventHandler(this.btnAddPurchase_Click);
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(6, 79);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(63, 13);
            this.label3.TabIndex = 5;
            this.label3.Text = "Description:";
            // 
            // txtDescription
            // 
            this.txtDescription.Location = new System.Drawing.Point(67, 79);
            this.txtDescription.Name = "txtDescription";
            this.txtDescription.Size = new System.Drawing.Size(200, 20);
            this.txtDescription.TabIndex = 3;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(6, 33);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(24, 13);
            this.label2.TabIndex = 3;
            this.label2.Text = "On:";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(6, 10);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(61, 13);
            this.label1.TabIndex = 2;
            this.label1.Text = "Paid for By:";
            // 
            // cbPaidBy
            // 
            this.cbPaidBy.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cbPaidBy.FormattingEnabled = true;
            this.cbPaidBy.Location = new System.Drawing.Point(67, 6);
            this.cbPaidBy.Name = "cbPaidBy";
            this.cbPaidBy.Size = new System.Drawing.Size(121, 21);
            this.cbPaidBy.TabIndex = 1;
            // 
            // purchaseDate
            // 
            this.purchaseDate.Location = new System.Drawing.Point(67, 33);
            this.purchaseDate.Name = "purchaseDate";
            this.purchaseDate.Size = new System.Drawing.Size(200, 20);
            this.purchaseDate.TabIndex = 2;
            // 
            // tpPayment
            // 
            this.tpPayment.Controls.Add(this.label9);
            this.tpPayment.Controls.Add(this.paymentDate);
            this.tpPayment.Controls.Add(this.cbPaidTo);
            this.tpPayment.Controls.Add(this.btnClearPayment);
            this.tpPayment.Controls.Add(this.btnAddPayment);
            this.tpPayment.Controls.Add(this.label4);
            this.tpPayment.Controls.Add(this.txtAmountPaid);
            this.tpPayment.Controls.Add(this.label5);
            this.tpPayment.Controls.Add(this.label6);
            this.tpPayment.Controls.Add(this.cbPaidFrom);
            this.tpPayment.Location = new System.Drawing.Point(4, 22);
            this.tpPayment.Name = "tpPayment";
            this.tpPayment.Padding = new System.Windows.Forms.Padding(3);
            this.tpPayment.Size = new System.Drawing.Size(332, 159);
            this.tpPayment.TabIndex = 1;
            this.tpPayment.Text = "Payment";
            this.tpPayment.UseVisualStyleBackColor = true;
            // 
            // label9
            // 
            this.label9.AutoSize = true;
            this.label9.Location = new System.Drawing.Point(8, 60);
            this.label9.Name = "label9";
            this.label9.Size = new System.Drawing.Size(24, 13);
            this.label9.TabIndex = 18;
            this.label9.Text = "On:";
            // 
            // paymentDate
            // 
            this.paymentDate.Location = new System.Drawing.Point(69, 60);
            this.paymentDate.Name = "paymentDate";
            this.paymentDate.Size = new System.Drawing.Size(200, 20);
            this.paymentDate.TabIndex = 3;
            // 
            // cbPaidTo
            // 
            this.cbPaidTo.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cbPaidTo.FormattingEnabled = true;
            this.cbPaidTo.Location = new System.Drawing.Point(69, 33);
            this.cbPaidTo.Name = "cbPaidTo";
            this.cbPaidTo.Size = new System.Drawing.Size(121, 21);
            this.cbPaidTo.TabIndex = 2;
            // 
            // btnClearPayment
            // 
            this.btnClearPayment.Location = new System.Drawing.Point(94, 109);
            this.btnClearPayment.Name = "btnClearPayment";
            this.btnClearPayment.Size = new System.Drawing.Size(75, 23);
            this.btnClearPayment.TabIndex = 6;
            this.btnClearPayment.Text = "Clear";
            this.btnClearPayment.UseVisualStyleBackColor = true;
            this.btnClearPayment.Click += new System.EventHandler(this.btnClearPayment_Click);
            // 
            // btnAddPayment
            // 
            this.btnAddPayment.Location = new System.Drawing.Point(11, 109);
            this.btnAddPayment.Name = "btnAddPayment";
            this.btnAddPayment.Size = new System.Drawing.Size(75, 23);
            this.btnAddPayment.TabIndex = 5;
            this.btnAddPayment.Text = "Save";
            this.btnAddPayment.UseVisualStyleBackColor = true;
            this.btnAddPayment.Click += new System.EventHandler(this.btnAddPayment_Click);
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(8, 86);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(46, 13);
            this.label4.TabIndex = 13;
            this.label4.Text = "Amount:";
            // 
            // txtAmountPaid
            // 
            this.txtAmountPaid.Location = new System.Drawing.Point(69, 83);
            this.txtAmountPaid.Name = "txtAmountPaid";
            this.txtAmountPaid.Size = new System.Drawing.Size(100, 20);
            this.txtAmountPaid.TabIndex = 4;
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(8, 33);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(23, 13);
            this.label5.TabIndex = 11;
            this.label5.Text = "To:";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(8, 10);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(33, 13);
            this.label6.TabIndex = 10;
            this.label6.Text = "From:";
            // 
            // cbPaidFrom
            // 
            this.cbPaidFrom.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cbPaidFrom.FormattingEnabled = true;
            this.cbPaidFrom.Location = new System.Drawing.Point(69, 6);
            this.cbPaidFrom.Name = "cbPaidFrom";
            this.cbPaidFrom.Size = new System.Drawing.Size(121, 21);
            this.cbPaidFrom.TabIndex = 1;
            // 
            // tpResident
            // 
            this.tpResident.Controls.Add(this.lvResidents);
            this.tpResident.Controls.Add(this.btnAddResident);
            this.tpResident.Controls.Add(this.txtName);
            this.tpResident.Controls.Add(this.label8);
            this.tpResident.Location = new System.Drawing.Point(4, 22);
            this.tpResident.Name = "tpResident";
            this.tpResident.Size = new System.Drawing.Size(332, 159);
            this.tpResident.TabIndex = 2;
            this.tpResident.Text = "Resident";
            this.tpResident.UseVisualStyleBackColor = true;
            // 
            // lvResidents
            // 
            this.lvResidents.Location = new System.Drawing.Point(163, 3);
            this.lvResidents.Name = "lvResidents";
            this.lvResidents.Size = new System.Drawing.Size(166, 136);
            this.lvResidents.TabIndex = 4;
            this.lvResidents.UseCompatibleStateImageBehavior = false;
            // 
            // btnAddResident
            // 
            this.btnAddResident.Location = new System.Drawing.Point(16, 35);
            this.btnAddResident.Name = "btnAddResident";
            this.btnAddResident.Size = new System.Drawing.Size(141, 23);
            this.btnAddResident.TabIndex = 2;
            this.btnAddResident.Text = "Add";
            this.btnAddResident.UseVisualStyleBackColor = true;
            this.btnAddResident.Click += new System.EventHandler(this.btnAddResident_Click);
            // 
            // txtName
            // 
            this.txtName.Location = new System.Drawing.Point(57, 9);
            this.txtName.Name = "txtName";
            this.txtName.Size = new System.Drawing.Size(100, 20);
            this.txtName.TabIndex = 1;
            // 
            // label8
            // 
            this.label8.AutoSize = true;
            this.label8.Location = new System.Drawing.Point(13, 12);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(38, 13);
            this.label8.TabIndex = 0;
            this.label8.Text = "Name:";
            // 
            // tcMoney
            // 
            this.tcMoney.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.tcMoney.Controls.Add(this.tabPage1);
            this.tcMoney.Controls.Add(this.tabPage2);
            this.tcMoney.Location = new System.Drawing.Point(12, 218);
            this.tcMoney.Name = "tcMoney";
            this.tcMoney.SelectedIndex = 0;
            this.tcMoney.Size = new System.Drawing.Size(790, 237);
            this.tcMoney.TabIndex = 2;
            // 
            // tabPage1
            // 
            this.tabPage1.Location = new System.Drawing.Point(4, 22);
            this.tabPage1.Name = "tabPage1";
            this.tabPage1.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage1.Size = new System.Drawing.Size(782, 211);
            this.tabPage1.TabIndex = 0;
            this.tabPage1.Text = "tabPage1";
            this.tabPage1.UseVisualStyleBackColor = true;
            // 
            // tabPage2
            // 
            this.tabPage2.Location = new System.Drawing.Point(4, 22);
            this.tabPage2.Name = "tabPage2";
            this.tabPage2.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage2.Size = new System.Drawing.Size(782, 211);
            this.tabPage2.TabIndex = 1;
            this.tabPage2.Text = "tabPage2";
            this.tabPage2.UseVisualStyleBackColor = true;
            // 
            // lblOwes
            // 
            this.lblOwes.AutoSize = true;
            this.lblOwes.Location = new System.Drawing.Point(386, 49);
            this.lblOwes.Name = "lblOwes";
            this.lblOwes.Size = new System.Drawing.Size(0, 13);
            this.lblOwes.TabIndex = 3;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(814, 480);
            this.Controls.Add(this.lblOwes);
            this.Controls.Add(this.tcMoney);
            this.Controls.Add(this.tabControl1);
            this.Controls.Add(this.menuStrip1);
            this.MainMenuStrip = this.menuStrip1;
            this.Name = "Form1";
            this.Text = "HouseBudget";
            this.Load += new System.EventHandler(this.Form1_Load);
            this.menuStrip1.ResumeLayout(false);
            this.menuStrip1.PerformLayout();
            this.tabControl1.ResumeLayout(false);
            this.tpReceipt.ResumeLayout(false);
            this.tpReceipt.PerformLayout();
            this.tpPayment.ResumeLayout(false);
            this.tpPayment.PerformLayout();
            this.tpResident.ResumeLayout(false);
            this.tpResident.PerformLayout();
            this.tcMoney.ResumeLayout(false);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.MenuStrip menuStrip1;
        private System.Windows.Forms.ToolStripMenuItem exitToolStripMenuItem;
        private System.Windows.Forms.TabControl tabControl1;
        private System.Windows.Forms.TabPage tpReceipt;
        private System.Windows.Forms.DateTimePicker purchaseDate;
        private System.Windows.Forms.TabPage tpPayment;
        private System.Windows.Forms.TabPage tpResident;
        private System.Windows.Forms.TextBox txtDescription;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.ComboBox cbPaidBy;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Button btnClearReceipt;
        private System.Windows.Forms.Button btnAddPurchase;
        private System.Windows.Forms.Button btnClearPayment;
        private System.Windows.Forms.Button btnAddPayment;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.TextBox txtAmountPaid;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.ComboBox cbPaidFrom;
        private System.Windows.Forms.ComboBox cbPaidTo;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.TextBox txtAmountPurchased;
        private System.Windows.Forms.Button btnAddResident;
        private System.Windows.Forms.TextBox txtName;
        private System.Windows.Forms.Label label8;
        private System.Windows.Forms.ToolStripMenuItem backupToolStripMenuItem;
        private System.Windows.Forms.ListView lvResidents;
        private System.Windows.Forms.TabControl tcMoney;
        private System.Windows.Forms.TabPage tabPage1;
        private System.Windows.Forms.TabPage tabPage2;
        private System.Windows.Forms.Label label9;
        private System.Windows.Forms.DateTimePicker paymentDate;
        private System.Windows.Forms.Label lblOwes;
        private System.Windows.Forms.Label label10;
        private System.Windows.Forms.ComboBox cbCategory;
    }
}

