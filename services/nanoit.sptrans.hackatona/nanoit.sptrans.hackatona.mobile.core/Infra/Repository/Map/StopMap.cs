using FluentNHibernate.Mapping;
using nanoit.sptrans.hackatona.core.domain.entity;

namespace nanoit.sptrans.hackatona.core.Infra.Repository.Map
{
    public sealed class StopMap : ClassMap<Stop>
    {
        public StopMap()
        {
            Table("tbCoO_Parada");
            Id(x => x.Id).Column("pk_int_parada");
            Map(x => x.Name).Column("vch_nome").Nullable();
            Map(x => x.Address).Column("vch_endereco").Not.Nullable();
            Map(x => x.Number).Column("int_numero").Not.Nullable();
            Map(x => x.Latitude).Column("vch_latitude").Not.Nullable();
            Map(x => x.Longitude).Column("vch_longitude").Not.Nullable();
            HasMany(x => x.StopLines).KeyColumn("FK_int_parada");
        }
    }
}


