using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace nanoit.sptrans.hackatona.core.domain.extensions
{
    public static class DateTimeExtensions
    {
        public static DateTime UTCBrazilDateTime
        {
            get
            {
                return DateTime.UtcNow.AddHours(-3);
            }
        }
    }
}
