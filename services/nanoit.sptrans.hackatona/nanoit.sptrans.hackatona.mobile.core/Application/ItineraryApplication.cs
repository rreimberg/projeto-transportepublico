using System;
using System.Collections.Generic;
using nanoit.sptrans.hackatona.core.domain;
using nanoit.sptrans.hackatona.core.Infra.Repository;
using nanoit.sptrans.hackatona.core.domain.entity;
using nanoit.sptrans.hackatona.core.Infra.Repository.Implements;

namespace nanoit.sptrans.hackatona.core.application
{
    public class ItineraryApplication
    {
        public Itinerary GetItinerary(int pId)
        {
            using (HibernateConnection hibernateConnection = new HibernateConnection())
            {
                using (HibernateRepository<Itinerary> hibernateRepository = new HibernateRepository<Itinerary>(hibernateConnection))
                {
                    var itinerary = hibernateRepository.FindOne(x => x.Id == pId);
                    return itinerary;
                }
            }
        }

        public bool SaveOrUpdateItinerary(Itinerary pItinerary)
        {
            using (HibernateConnection hibernateConnection = new HibernateConnection())
            {
                using (HibernateRepository<Itinerary> hibernateRepository = new HibernateRepository<Itinerary>(hibernateConnection))
                {
                    var insertEntity = hibernateRepository.SaveOrUpdate(pItinerary);
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

        //public IEnumerable<Itinerary> FindManyItinerary(string pPrefixId, int pDirection)
        //{
        //    using (HibernateConnection hibernateConnection = new HibernateConnection())
        //    {
        //        using (HibernateRepository<Itinerary> hibernateRepository = new HibernateRepository<Itinerary>(hibernateConnection))
        //        {
        //            var itinerary = hibernateRepository.FindMany(x => x.Line.PrefixLabel == pPrefixId && x.Line.Direction == pDirection);
        //            return itinerary;
        //        }
        //    }
        //}

        //public IEnumerable<Itinerary> FindManyItinerary(string pSPTransId)
        //{
        //    using (HibernateConnection hibernateConnection = new HibernateConnection())
        //    {
        //        using (HibernateRepository<Itinerary> hibernateRepository = new HibernateRepository<Itinerary>(hibernateConnection))
        //        {
        //            var itinerary = hibernateRepository.FindMany(x => x.Line.SPTransId == pSPTransId);
        //            return itinerary;
        //        }
        //    }
        //}

        public IEnumerable<Itinerary> FindManyItinerary(string pSPTransId)
        {
            using (ItineraryRepository repository = new ItineraryRepository())
            {
                return repository.FindManyItinerary(pSPTransId);
            }

        }
    }
}
