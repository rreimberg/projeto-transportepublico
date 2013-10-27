using nanoit.sptrans.hackatona.core.domain.Abstract;
using nanoit.sptrans.hackatona.core.Infra.Repository;
using nanoit.sptrans.hackatona.core.domain.entity;
using nanoit.sptrans.hackatona.core.domain.service;
using Ninject.Modules;

namespace nanoit.sptrans.hackatona.core.domain.service.ioc
{
    public class RepositoryModule : NinjectModule
    {
        public override void Load()
        {
            Bind<ILog>().To<Log>();
            Bind<IHibernateConnection>().To<HibernateConnection>();
            Bind<IHibernateRepository<domain.entity.Application>>().To<HibernateRepository<domain.entity.Application>>();
            Bind<IHibernateRepository<ApplicationWarning>>().To<HibernateRepository<ApplicationWarning>>();
            Bind<IHibernateRepository<Passage>>().To<HibernateRepository<Passage>>();
            Bind<IHibernateRepository<Device>>().To<HibernateRepository<Device>>();
            Bind<IHibernateRepository<Feature>>().To<HibernateRepository<Feature>>();
            Bind<IHibernateRepository<Itinerary>>().To<HibernateRepository<Itinerary>>();
            Bind<IHibernateRepository<Line>>().To<HibernateRepository<Line>>();
            Bind<IHibernateRepository<LineDetail>>().To<HibernateRepository<LineDetail>>();
            Bind<IHibernateRepository<LogAccess>>().To<HibernateRepository<LogAccess>>();
            Bind<IHibernateRepository<LogFeature>>().To<HibernateRepository<LogFeature>>();
            Bind<IHibernateRepository<LogSearch>>().To<HibernateRepository<LogSearch>>();
            Bind<IHibernateRepository<Bus>>().To<HibernateRepository<Bus>>();
            Bind<IHibernateRepository<Plataform>>().To<HibernateRepository<Plataform>>();
            Bind<IHibernateRepository<Suggestion>>().To<HibernateRepository<Suggestion>>();
            Bind<IHibernateRepository<BusType>>().To<HibernateRepository<BusType>>();
            Bind<IHibernateRepository<BusTypeSuggestion>>().To<HibernateRepository<BusTypeSuggestion>>();
        }
    }
}
