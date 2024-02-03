package net.md_5.bungee;

import org.junit.jupiter.api.Test;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ThrottleTest
{

    private static class FixedTicker implements com.github.benmanes.caffeine.cache.Ticker {

        private long value;

        @Override
        public long read()
        {
            return value;
        }
    }


    @Test
    public void testThrottle() throws UnknownHostException
    {
        FixedTicker ticker = new FixedTicker();
        ConnectionThrottle throttle = new ConnectionThrottle(ticker, 10, 3 );
        InetSocketAddress address;

        try
        {
            address = new InetSocketAddress( InetAddress.getLocalHost(), 0 );
        } catch ( UnknownHostException ex )
        {
            address = new InetSocketAddress( InetAddress.getByName( null ), 0 );
        }

        assertFalse( throttle.throttle( address ), "Address should not be throttled" ); // 1
        assertFalse( throttle.throttle( address ), "Address should not be throttled" ); // 2
        assertFalse( throttle.throttle( address ), "Address should not be throttled" ); // 3
        assertTrue( throttle.throttle( address ), "Address should be throttled" ); // The 3rd one must be throttled, but also increased the count to 4

        throttle.unthrottle( address ); // We are back at 3, next attempt will make it 4 and throttle
        throttle.unthrottle( address ); // Now we are at 2, will not be throttled
        assertFalse( throttle.throttle( address ), "Address should not be throttled" ); // 3
        assertTrue( throttle.throttle( address ), "Address should be throttled" ); // 4

        // Now test expiration
        ticker.value += TimeUnit.MILLISECONDS.toNanos( 50 );
        assertFalse( throttle.throttle( address ), "Address should not be throttled" );
    }
}
