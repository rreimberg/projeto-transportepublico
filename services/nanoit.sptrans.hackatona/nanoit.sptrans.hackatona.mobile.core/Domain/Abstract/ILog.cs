using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace nanoit.sptrans.hackatona.core.domain.service
{
    public interface ILog
    {
        void ExceptionLog(Exception ex);
    }
}
