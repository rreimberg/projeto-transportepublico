using System;
using System.Collections.Generic;
using FluentNHibernate.Mapping;
using nanoit.sptrans.hackatona.core.domain;
using nanoit.sptrans.hackatona.core.domain.entity;

namespace nanoit.sptrans.hackatona.core.Infra.Repository.Map
{
    public sealed class DeviceMap : ClassMap<Device>
    {
        public DeviceMap()
        {
            Table("tbCoO_Dispositivo");
            Id(x => x.Id).Column("PK_vch_DeviceId").GeneratedBy.Assigned();
            Map(x => x.IMEI).Column("vch_IMEI").Nullable();
            References(x => x.Plataform).Column("FK_int_Plataforma").Nullable();
            Map(x => x.OperationSystem).Column("vch_SistemaOperacional").Nullable();
            Map(x => x.DeviceModel).Column("vch_ModeloDispositivo").Nullable();
            Map(x => x.DateTimeOfCreation).Column("dtt_Inclusao").Not.Nullable();
        }
    }
}


