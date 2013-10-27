using System;
using System.Collections.Generic;
using nanoit.sptrans.hackatona.core.domain;
using nanoit.sptrans.hackatona.core.Infra.Repository;
using nanoit.sptrans.hackatona.core.domain.entity;

namespace nanoit.sptrans.hackatona.core.application
{
    public class FeatureApplication
    {
        public Feature GetFeature(int pId)
        {
            using (HibernateConnection hibernateConnection = new HibernateConnection())
            {
                using (HibernateRepository<Feature> hibernateRepository = new HibernateRepository<Feature>(hibernateConnection))
                {
                    var feature = hibernateRepository.FindOne(x => x.Id == pId);
                    return feature;
                }
            }
        }

        public bool SaveOrUpdateFeature(Feature pFeature)
        {
            using (HibernateConnection hibernateConnection = new HibernateConnection())
            {
                using (HibernateRepository<Feature> hibernateRepository = new HibernateRepository<Feature>(hibernateConnection))
                {
                    var insertEntity = hibernateRepository.SaveOrUpdate(pFeature);
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
