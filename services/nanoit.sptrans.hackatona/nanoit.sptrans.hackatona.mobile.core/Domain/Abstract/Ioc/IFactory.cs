using System;
using System.Collections.Generic;

namespace nanoit.sptrans.hackatona.core.domain.Abstract.Ioc
{
    public interface IFactory : IDisposable
    {
        T Get<T>();
    }
}
