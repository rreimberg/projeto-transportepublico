namespace nanoit.sptrans.hackatona.core.domain.entity
{
    public class StopLineId
    {
        public virtual Stop Stop { get; set; }
        public virtual string Prefix { get; set; }
        public virtual string PrefixType { get; set; }
        public virtual int Direction { get; set; }

        public override bool Equals(object obj)
        {
            return Equals(obj as StopLineId);
        }

        private bool Equals(StopLineId other)
        {
            if (ReferenceEquals(other, null)) return false;
            if (ReferenceEquals(other, this)) return true;

            return Stop == other.Stop &&
                   Prefix == other.Prefix &&
                   PrefixType == other.PrefixType &&
                   Direction == other.Direction;
        }

        public override int GetHashCode()
        {
            unchecked
            {
                var hash = GetType().GetHashCode();
                hash = (hash * 33) ^ Stop.GetHashCode();
                hash = (hash * 33) ^ Prefix.GetHashCode();
                hash = (hash * 33) ^ PrefixType.GetHashCode();
                hash = (hash * 33) ^ Direction.GetHashCode();

                return hash;
            }
        }
    }
}