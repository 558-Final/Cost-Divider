using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace HouseBudget
{
    class Trans : IComparable
    {
        private string type;
        private string date;
        private string amount;
        private string paidBy;
        private string paidTo;
        private string description;
        private string category;
        private string dateEntered;

        

        public Trans()
        {
        }

        public Trans(string type, string date, string amount, string paidBy, string paidTo, string desc,string category)
        {
            this.type = type;
            this.date = date;
            this.amount = amount;
            this.paidBy = paidBy;
            this.paidTo = paidTo;
            this.description = desc;
            this.category = category;
            this.dateEntered = DateTime.Now.ToString("MM/dd/yyyy hh:mm tt");
        }
        public string Type
        {
            get { return type; }
            set { type = value; }
        }
        public string Date
        {
            get { return date; }
            set { date = value; }
        }
        public string Amount
        {
            get { return amount; }
            set { amount = value; }
        }
        public string PaidBy
        {
            get { return paidBy; }
            set { paidBy = value; }
        }
        public string PaidTo
        {
            get { return paidTo; }
            set { paidTo = value; }
        }
        public string Description
        {
            get { return description; }
            set { description = value; }
        }

        public string Category
        {
            get { return category; }
            set { category = value; }
        }

        public int CompareTo(object obj)
        {
            return DateTime.Parse(this.date).CompareTo(DateTime.Parse(((Trans)obj).date));
        }
        public string DateEntered
        {
            get { return dateEntered; }
            set { dateEntered = value; }
        }
    }
}
