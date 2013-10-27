using System;
using System.Collections.Generic;
using nanoit.sptrans.hackatona.core.domain;
using nanoit.sptrans.hackatona.core.Infra.Repository;
using nanoit.sptrans.hackatona.core.domain.entity;

namespace nanoit.sptrans.hackatona.core.application
{
    public class BusTypeApplication
    {
        public BusType GetBusType(int pId)
        {
            using (HibernateConnection hibernateConnection = new HibernateConnection())
            {
                using (HibernateRepository<BusType> hibernateRepository = new HibernateRepository<BusType>(hibernateConnection))
                {
                    var busType = hibernateRepository.FindOne(x => x.Id == pId);
                    return busType;
                }
            }
        }


        public bool SaveOrUpdateBusType(BusType pBusType)
        {
            using (HibernateConnection hibernateConnection = new HibernateConnection())
            {
                using (HibernateRepository<BusType> hibernateRepository = new HibernateRepository<BusType>(hibernateConnection))
                {
                    var insertEntity = hibernateRepository.SaveOrUpdate(pBusType);
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
