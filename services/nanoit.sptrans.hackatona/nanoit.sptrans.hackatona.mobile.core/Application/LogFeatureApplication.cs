using System;
using System.Collections.Generic;
using nanoit.sptrans.hackatona.core.domain;
using nanoit.sptrans.hackatona.core.Infra.Repository;
using nanoit.sptrans.hackatona.core.domain.entity;

namespace nanoit.sptrans.hackatona.core.application
{
    public class LogFeatureApplication
    {
        public LogFeature GetLogFeature(int pId)
        {
            using (HibernateConnection hibernateConnection = new HibernateConnection())
            {
                using (HibernateRepository<LogFeature> hibernateRepository = new HibernateRepository<LogFeature>(hibernateConnection))
                {
                    var logFeature = hibernateRepository.FindOne(x => x.Id == pId);
                    return logFeature;
                }
            }
        }

        public bool SaveOrUpdateLogFeature(LogFeature pLogFeature)
        {
            using (HibernateConnection hibernateConnection = new HibernateConnection())
            {
                using (HibernateRepository<LogFeature> hibernateRepository = new HibernateRepository<LogFeature>(hibernateConnection))
                {
                    var insertEntity = hibernateRepository.SaveWithoutTransaction(pLogFeature);
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
