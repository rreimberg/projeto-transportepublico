using System;
using System.Collections.Generic;
using nanoit.sptrans.hackatona.core.domain;
using nanoit.sptrans.hackatona.core.Infra.Repository;
using nanoit.sptrans.hackatona.core.domain.entity;

namespace nanoit.sptrans.hackatona.core.application
{
    public class BusApplication
    {
        public Bus GetBus(int pId)
        {
            using (HibernateConnection hibernateConnection = new HibernateConnection())
            {
                using (HibernateRepository<Bus> hibernateRepository = new HibernateRepository<Bus>(hibernateConnection))
                {
                    var bus = hibernateRepository.FindOne(x => x.Id == pId);
                    return bus;
                }
            }
        }

        public bool SaveOrUpdateBus(Bus pBus)
        {
            using (HibernateConnection hibernateConnection = new HibernateConnection())
            {
                using (HibernateRepository<Bus> hibernateRepository = new HibernateRepository<Bus>(hibernateConnection))
                {
                    var insertEntity = hibernateRepository.SaveOrUpdate(pBus);
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

        public bool UpdateBus(Bus pBus)
        {
            using (HibernateConnection hibernateConnection = new HibernateConnection())
            {
                using (HibernateRepository<Bus> hibernateRepository = new HibernateRepository<Bus>(hibernateConnection))
                {
                    var insertEntity = hibernateRepository.Update(pBus);
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
