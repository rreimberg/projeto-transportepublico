using System;
using System.Collections.Generic;

namespace nanoit.sptrans.hackatona.core.domain.Abstract
{
    public interface IConnection<T> : IDisposable
    {
        T Connection { get; set; }
    }
}
