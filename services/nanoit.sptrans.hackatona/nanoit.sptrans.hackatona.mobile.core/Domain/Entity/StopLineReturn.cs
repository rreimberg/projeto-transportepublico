namespace nanoit.sptrans.hackatona.core.domain.entity
{
    public class StopLineReturn
    {
        public int Id { get; set; }
        public string Prefix { get; set; }
        public int PrefixType { get; set; }
        public int Direction { get; set; }
        public string OlhoVivoId { get; set; }
        public string Target { get; set; }
    }
}
