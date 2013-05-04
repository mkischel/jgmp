package com.github.tasubo.jgmp;

import org.junit.Test;

import static com.github.tasubo.jgmp.Mocks.*;
import static com.github.tasubo.jgmp.MpAssert.*;
import static org.junit.Assert.assertThat;

public class ReferrerTest {
    @Test
    public void shouldSendReferrerInfo() {
        MpClient client = prepareMpClient();
        Sendable sendable = prepareSendable();

        Referrer referrer = Referrer.from("http://example.com");

        client.send(referrer.with(sendable));


        assertThat(getRequestLog().last(), hasParam("dr").withBareValue("http%3A%2F%2Fexample.com"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void paramLength() {
        MpClient client = prepareMpClient();
        Sendable sendable = prepareSendable();

        Referrer referrer = Referrer.from(stringWithLength(2049));

        client.send(referrer.with(sendable));
    }

    @Test
    public void shouldAllow2048Length() {
        MpClient client = prepareMpClient();
        Sendable sendable = prepareSendable();

        Referrer referrer = Referrer.from(stringWithLength(2048));

        client.send(referrer.with(sendable));
    }
}