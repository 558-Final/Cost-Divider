using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Xml;
using System.Xml.Linq;

namespace HouseBudget
{
    public partial class DelinquentEntries : Form
    {
        public DelinquentEntries(string path)
        {
            InitializeComponent();
            XmlDocument doc = new XmlDocument();
            doc.Load(path);
            foreach (XmlNode month in doc.GetElementsByTagName("months")[0].ChildNodes)
            {
                 Console.WriteLine("a");
            }
        }
    }
}
