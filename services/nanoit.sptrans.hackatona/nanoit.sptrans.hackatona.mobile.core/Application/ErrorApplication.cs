using System;
using System.Collections.Generic;
using nanoit.sptrans.hackatona.core.domain;
using nanoit.sptrans.hackatona.core.Infra.Repository;
using nanoit.sptrans.hackatona.core.domain.entity;

namespace nanoit.sptrans.hackatona.core.application
{
    public class ErrorApplication
    {
        public Error GetError(int pId)
        {
            using (HibernateConnection hibernateConnection = new HibernateConnection())
            {
                using (HibernateRepository<Error> hibernateRepository = new HibernateRepository<Error>(hibernateConnection))
                {
                    var error = hibernateRepository.FindOne(x => x.Id == pId);
                    return error;
                }
            }
        }

        public bool SaveOrUpdateSuggestion(Error pError)
        {
            using (HibernateConnection hibernateConnection = new HibernateConnection())
            {
                using (HibernateRepository<Error> hibernateRepository = new HibernateRepository<Error>(hibernateConnection))
                {
                    var insertEntity = hibernateRepository.SaveOrUpdate(pError);
                    if (insertEntity == null)
                    {
                        return false;
                    }
                    else
                    {
                        return true;
                    }
                }
            }
        }
    }
}
