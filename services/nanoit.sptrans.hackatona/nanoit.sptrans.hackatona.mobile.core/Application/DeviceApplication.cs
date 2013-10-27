using System;
using System.Collections.Generic;
using nanoit.sptrans.hackatona.core.domain;
using nanoit.sptrans.hackatona.core.Infra.Repository;
using nanoit.sptrans.hackatona.core.domain.entity;

namespace nanoit.sptrans.hackatona.core.application
{
    public class DeviceApplication
    {
        public Device GetDevice(string pId)
        {
            using (HibernateConnection hibernateConnection = new HibernateConnection())
            {
                using (HibernateRepository<Device> hibernateRepository = new HibernateRepository<Device>(hibernateConnection))
                {
                    var device = hibernateRepository.FindOne(x => x.Id == pId);
                    return device;
                }
            }
        }

        public bool SaveOrUpdateDevice(Device pDevice)
        {
            using (HibernateConnection hibernateConnection = new HibernateConnection())
            {
                using (HibernateRepository<Device> hibernateRepository = new HibernateRepository<Device>(hibernateConnection))
                {
                    var insertEntity = hibernateRepository.SaveOrUpdate(pDevice);
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
