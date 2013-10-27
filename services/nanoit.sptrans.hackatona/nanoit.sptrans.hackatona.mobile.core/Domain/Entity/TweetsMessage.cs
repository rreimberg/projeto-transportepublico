using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace nanoit.sptrans.hackatona.mobile.core.Domain.Entity
{
    public class Messages
    {
        public string created_at { get; set; }
        public string text { get; set; }
        public object id { get; set; }
    }

    public class RootObject
    {
        public List<Messages> messages { get; set; }
    }
}
