
/**
 * Copyright Connectiva Systems, Inc @2009. All Rights Reserved. This software is the
 * proprietary information of Connectiva Systems, Inc. Use is subject to license
 * terms.
 *
 * Project: ParserGenX	
***/

/***
 * Package Declaration :
 *---------------------*/
 package ink.jasn.ded.util.common;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


/***
 * Packages and Classes Import :
 *-----------------------------*/

/**
 * Source  : DecodeUtil.java
 * Author  : Nirmal Kumar Biswas
 * Date    : Jul 27, 2009
 * Time    : 9:22:38 PM
 * Version : $0.01
 **/
public class DecodeUtil
{
    /**
     * Use to parse UTCTime.
     * <p>
     * UTCTime formats are:
     * 
     * <pre>
     *        yymmddhhmmZ
     *        yymmddhhmmssZ
     *        yymmddhhmm+hhmm
     *        yymmddhhmm-hhmm
     *        yymmddhhmmss+hhmm
     *        yymmddhhmmss-hhmm
     * </pre>
     */
    public static final Date toUTCTime( byte[] buffer ) // throws BerFormatException
    {
        int limit = buffer.length;
        /*
         * if (limit != 11 && limit != 13 && limit != 15 && limit != 17) throw new
         * BerFormatException(Tag.UTC_TIME); if (limit == 11 && buffer[10] != 'Z') throw new
         * BerFormatException(Tag.UTC_TIME); if (limit == 13 && buffer[12] != 'Z') throw new
         * BerFormatException(Tag.UTC_TIME); if (limit == 15 && (buffer[10] != '+' || buffer[10] !=
         * '-')) throw new BerFormatException(Tag.UTC_TIME); if (limit == 17 && (buffer[12] != '+' ||
         * buffer[12] != '-')) throw new BerFormatException(Tag.UTC_TIME);
         */
        int YY = (buffer[0] - '0') * 10 + (buffer[1] - '0');
        int MM = (buffer[2] - '0') * 10 + (buffer[3] - '0') - 1;
        int DD = (buffer[4] - '0') * 10 + (buffer[5] - '0');
        int hh = (buffer[6] - '0') * 10 + (buffer[7] - '0');
        int mm = (buffer[8] - '0') * 10 + (buffer[9] - '0');
        YY += YY <= 50 ? 2000 : 1900; // fails for 2051 and later
        Date result = null;
        Calendar cal = null;
        int ss = 0;
        switch (limit)
        {
            case 11:
                cal = Calendar.getInstance( TimeZone.getTimeZone( "UTC" ) );
                cal.set( YY, MM, DD, hh, mm );
                result = cal.getTime();
                break;
            case 13:
                cal = Calendar.getInstance( TimeZone.getTimeZone( "UTC" ) );
                ss = (buffer[10] - '0') * 10 + (buffer[11] - '0');
                cal.set( YY, MM, DD, hh, mm, ss );
                result = cal.getTime();
                break;
            case 15:
                cal = Calendar.getInstance();
                cal.set( YY, MM, DD, hh, mm );
                hh = (buffer[11] - '0') * 10 + (buffer[12] - '0');
                mm = (buffer[13] - '0') * 10 + (buffer[14] - '0');
                mm += hh * 60;
                if( buffer[10] == '+' )
                    cal.add( Calendar.MINUTE, mm );
                else
                    cal.add( Calendar.MINUTE, -mm );
                result = cal.getTime();
                break;
            case 17:
                cal = Calendar.getInstance();
                ss = (buffer[10] - '0') * 10 + (buffer[11] - '0');
                cal.set( YY, MM, DD, hh, mm, ss );
                hh = (buffer[13] - '0') * 10 + (buffer[14] - '0');
                mm = (buffer[15] - '0') * 10 + (buffer[16] - '0');
                mm += hh * 60;
                if( buffer[12] == '+' )
                    cal.add( Calendar.MINUTE, mm );
                else
                    cal.add( Calendar.MINUTE, -mm );
                result = cal.getTime();
        }

        return result;
    }


    /**
     * Use to parse GeneralizedTime.
     * <p>
     * GeneralizedTime use 4-digit for the year and an arbitrary number/precision for the seconds.
     * Its formats are:
     * 
     * <pre>
     *        yyyymmddhhmmZ
     *        yyyymmddhhmmssZ
     *        yyyymmddhhmmss.ss..sZ
     *        yyyymmddhhmm+hhmm
     *        yyyymmddhhmm-hhmm
     *        yyyymmddhhmmss+hhmm
     *        yyyymmddhhmmss-hhmm
     *        yyyymmddhhmmss.ss..s+hhmm
     *        yyyymmddhhmmss.ss..s-hhmm
     * </pre>
     * 
     * Please note that while GeneralizedTime allows for an arbitrary precision for the fraction of
     * a second field (digits after the decimal point) this implementation, relying on the Java
     * <tt>java.util.Date</tt> and <tt>java.util.Calendar</tt> classes, only caters for time
     * precision up to the millisecond --first 3 digits after the decimal point if/when encountered.
     * If a loss of precision is detected, a warning message is generated in the trace/debug stream,
     * but no exception is thrown.
     * @param buffer the raw bytes from a BER stream to be interpreted as a GeneralizedTime value.
     * @return a <tt>java.util.Date</tt> instance emboddying this value.
     */
    public static final Date toGeneralizedTime( byte[] buffer )// throws BerFormatException
    {
        int limit = buffer.length;
        // if (limit < 13)
        // throw new BerFormatException(Tag.GENERALIZED_TIME);
        int YY = (buffer[0] - '0') * 1000 + (buffer[1] - '0') * 100 + (buffer[2] - '0') * 10 + (buffer[3] - '0');
        int MM = (buffer[4] - '0') * 10 + (buffer[5] - '0') - 1;
        int DD = (buffer[6] - '0') * 10 + (buffer[7] - '0');
        int hh = (buffer[8] - '0') * 10 + (buffer[9] - '0');
        int mm = (buffer[10] - '0') * 10 + (buffer[11] - '0');
        Date result = null;
        Calendar cal = null;
        int ss = 0;
        int ms = 0;
        int precision = 0;
        int b = 0;
        boolean millis = false;
        if( buffer[limit - 1] == 'Z' )
        {
            for( int i = 12; i < limit - 1; )
            {
                b = buffer[i++] & 0xFF;
                if( b == '.' )
                    millis = true;
                else if( !millis )
                    ss = ss * 10 + (b - '0');
                else if( precision++ < 3 )
                    ms = ms * 10 + (b - '0');
            }

            cal = Calendar.getInstance( TimeZone.getTimeZone( "UTC" ) );
            cal.set( YY, MM, DD, hh, mm, ss );
            cal.set( Calendar.MILLISECOND, ms );
        } else
        {
            int i = 12;
            while( buffer[i] != '+' && buffer[i] != '-' && i < limit )
            {
                b = buffer[i++] & 0xFF;
                if( b == '.' )
                    millis = true;
                else if( !millis )
                    ss = ss * 10 + (b - '0');
                else if( precision++ < 3 )
                    ms = ms * 10 + (b - '0');
            }

            // if (i == limit)
            // throw new BerFormatException(Tag.GENERALIZED_TIME);

            cal = Calendar.getInstance();
            cal.set( YY, MM, DD, hh, mm, ss );
            cal.set( Calendar.MILLISECOND, ms );
            boolean toSubtract = (buffer[i++] == '-');
            // if ((limit - i) != 4)
            // throw new BerFormatException(Tag.GENERALIZED_TIME);

            hh = (buffer[i++] - '0') * 10 + (buffer[i++] - '0');
            mm = (buffer[i++] - '0') * 10 + (buffer[i++] - '0');
            mm += hh * 60;
            if( toSubtract )
                mm *= -1;

            cal.add( Calendar.MINUTE, mm );
        }

        result = cal.getTime();
        return result;
    }

    /**
     * @param buffer
     * @return
     */
    public static final String toOID( byte[] buffer )
    {
        StringBuffer sb = new StringBuffer();
        int length = buffer.length;
        int i = 0;
        if( --length >= 0 ) // first byte is special
        {
            int b = buffer[i++] & 0xFF;
            int first = (b < 40 ? 0 : (b < 80 ? 1 : 2));
            int second = (b - first * 40);
            sb.append( first ).append( "." ).append( second );
        }

        while( length > 0 ) // handle the rest
        {
            sb.append( "." );
            int sid = 0; // subid
            int b;
            do
            {

                b = buffer[i++] & 0xFF;
                sid = sid << 7 | (b & 0x7F);
            } while( --length > 0 && (b & 0x80) == 0x80 );

            sb.append( sid );
        }

        String result = sb.toString();
        return (result);
    }

    /**
     * @param ints
     * @return
     */
    public static String toHexString( byte[] ints )
    {
        StringBuffer tempBuff = new StringBuffer();
        int i = 0;
        int left;
        int right;
        int length = ints.length;
        while( i < length )
        {
            left = (ints[i] & 0xF0) >>> 4;
            right = ints[i] & 0x0F;
            toIntegerToHexValue( tempBuff, left );
            toIntegerToHexValue( tempBuff, right );
            i++;
        }
        return tempBuff.toString();
    }


    /**
     * @param bcdValue
     * @param number
     */
    private static void toIntegerToHexValue( StringBuffer bcdValue, int number )
    {
        if( number <= 0x9 )
            bcdValue.append( number );
        else if( number == 0x0A )
            bcdValue.append( "A" ); // 0x0A for "*"
        else if( number == 0x0B )
            bcdValue.append( "B" ); // 0x0B for "#"
        else if( number == 0x0C )
            bcdValue.append( "C" ); // 0x0C for "C"
        else if( number == 0x0D )
            bcdValue.append( "D" ); // 0x0D for "D"
        else if( number == 0x0E )
            bcdValue.append( "E" ); // 0x0E for "E"
        else if( number == 0x0F )
            bcdValue.append( "F" ); // 0x0E for "F"
    }
}


