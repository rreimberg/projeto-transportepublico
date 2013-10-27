using System.Configuration;
using NHibernate;
using FluentNHibernate.Cfg;
using FluentNHibernate.Cfg.Db;
using nanoit.sptrans.hackatona.core.domain.Abstract;
using nanoit.sptrans.hackatona.core.Infra.Repository.Map;
using FluentNHibernate.Conventions.Helpers;

namespace nanoit.sptrans.hackatona.core.Infra.Repository
{
    public class HibernateConnection : IHibernateConnection
    {
        #region IConnection<ISession> Members

        public ISession Connection
        {
            get
            {
                if (sessionFactory == null)
                {
                    CreateSession();
                }

                if (connection == null || !connection.IsOpen)
                {
                    connection = sessionFactory.OpenSession();
                }

                return connection;
            }
            set
            {
                connection = value;
            }
        }

        private static ISessionFactory sessionFactory;
        private ISession connection;

        #endregion

        public HibernateConnection()
        {
            //CreateSession();
        }

        private static void CreateSession()
        {
            sessionFactory = Fluently.Configure()
                                   .Database(MsSqlConfiguration.MsSql2008
                                   .ConnectionString(ConfigurationManager.ConnectionStrings["CoO"].ConnectionString)
                                   )
                                   .Mappings(m => m.FluentMappings.AddFromAssemblyOf<LogAccessMap>()
                                   .Conventions.Add(DefaultLazy.Always()))
                                   .BuildSessionFactory();
        }

        #region IDisposable Members

        public void Dispose()
        {
            if (connection != null)
                connection.Dispose();
        }

        #endregion

    }














    public class HibernateConnectionStateless : IHibernateConnectionStateless
    {
        #region IConnection<ISession> Members

        private IStatelessSession connection;
        public IStatelessSession Connection
        {
            get
            {
                if (this.connection == null)
                    this.CreateSession();

                return this.connection;
            }
            set
            {
                this.connection = value;
            }
        }
        #endregion

        public HibernateConnectionStateless()
        {
            CreateSession();
        }

        private void CreateSession()
        {
            Connection = Fluently.Configure()
                                   .Database(MsSqlConfiguration.MsSql2008
                                   .ConnectionString(ConfigurationManager.ConnectionStrings["CoO"].ConnectionString)
                                   )
                                   .Mappings(m => m.FluentMappings.AddFromAssemblyOf<LogAccessMap>()
                                   .Conventions.Add(DefaultLazy.Never()))
                                   .ExposeConfiguration(config =>
                                   {
                                       config.SetProperty("adonet.batch_size", "1000");
                                       config.SetProperty("command_timeout", "10000");
                                   })
                                   .BuildSessionFactory()
                                   .OpenStatelessSession();
        }

        #region IDisposable Members

        public void Dispose()
        {
            if (Connection != null)
                Connection.Dispose();
        }

        #endregion
    }
}
