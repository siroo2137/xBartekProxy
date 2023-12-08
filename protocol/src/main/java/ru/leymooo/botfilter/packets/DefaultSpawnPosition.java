package ru.leymooo.botfilter.packets;

import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.md_5.bungee.protocol.AbstractPacketHandler;
import net.md_5.bungee.protocol.DefinedPacket;
import net.md_5.bungee.protocol.ProtocolConstants;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DefaultSpawnPosition extends DefinedPacket
{

    private int posX;
    private int posY;
    private int posZ;
    private float angle;

    @Override
    public void write(ByteBuf buf, ProtocolConstants.Direction direction, int protocolVersion)
    {
        long location;
        if ( protocolVersion < ProtocolConstants.MINECRAFT_1_14 )
        {
            location = ( ( this.posX & 0x3FFFFFFL ) << 38 ) | ( ( this.posY & 0xFFFL ) << 26 ) | ( this.posZ & 0x3FFFFFFL );
        } else
        {
            location = ( ( this.posX & 0x3FFFFFFL ) << 38 ) | ( ( this.posZ & 0x3FFFFFFL ) << 12 ) | ( this.posY & 0xFFFL );
        }

        buf.writeLong( location );

        if ( protocolVersion >= ProtocolConstants.MINECRAFT_1_17 )
        {
            buf.writeFloat( this.angle );
        }
    }

    @Override
    public void handle(AbstractPacketHandler handler) throws Exception
    {
        throw new UnsupportedOperationException();
    }


}
