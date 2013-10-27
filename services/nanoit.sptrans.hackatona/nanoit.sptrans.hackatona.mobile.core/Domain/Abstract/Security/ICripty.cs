using System;
using System.Collections.Generic;

namespace nanoit.sptrans.hackatona.core.domain.Abstract.security
{
    public interface ICripty
    {
        String Cripty(String value);

        String Decrypt(String value);
    }
}
