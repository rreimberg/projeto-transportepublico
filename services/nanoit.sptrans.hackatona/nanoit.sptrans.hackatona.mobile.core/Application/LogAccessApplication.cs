using System;
using System.Collections.Generic;
using nanoit.sptrans.hackatona.core.domain;
using nanoit.sptrans.hackatona.core.Infra.Repository;
using nanoit.sptrans.hackatona.core.domain.entity;

namespace nanoit.sptrans.hackatona.core.application
{
    public class LogAccessApplication
    {
        public LogAccess GetLogAccess(int pId)
        {
            using (HibernateConnection hibernateConnection = new HibernateConnection())
            {
                using (HibernateRepository<LogAccess> hibernateRepository = new HibernateRepository<LogAccess>(hibernateConnection))
                {
                    var logAccess = hibernateRepository.FindOne(x => x.Id == pId);
                    return logAccess;
                }
            }
        }

        public bool SaveOrUpdateLogAccess(LogAccess pLogAccess)
        {
            using (HibernateConnection hibernateConnection = new HibernateConnection())
            {
                using (HibernateRepository<LogAccess> hibernateRepository = new HibernateRepository<LogAccess>(hibernateConnection))
                {
                    var insertEntity = hibernateRepository.SaveWithoutTransaction(pLogAccess);
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