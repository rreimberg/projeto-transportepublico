using System;
using System.Collections.Generic;
using nanoit.sptrans.hackatona.core.domain;
using nanoit.sptrans.hackatona.core.Infra.Repository;
using nanoit.sptrans.hackatona.core.domain.entity;

namespace nanoit.sptrans.hackatona.core.application
{
    public class LogSearchApplication
    {
        public LogSearch GetLogSearch(int pId)
        {
            using (HibernateConnection hibernateConnection = new HibernateConnection())
            {
                using (HibernateRepository<LogSearch> hibernateRepository = new HibernateRepository<LogSearch>(hibernateConnection))
                {
                    var logSearch = hibernateRepository.FindOne(x => x.Id == pId);
                    return logSearch;
                }
            }
        }

        public bool SaveOrUpdateLogSearch(LogSearch pLogSearch)
        {
            using (HibernateConnection hibernateConnection = new HibernateConnection())
            {
                using (HibernateRepository<LogSearch> hibernateRepository = new HibernateRepository<LogSearch>(hibernateConnection))
                {
                    var insertEntity = hibernateRepository.SaveOrUpdate(pLogSearch);
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
