package com.zhr.myapplication.bean.whois;

public class WhoisBean {

    /**
     * success : 1
     * result : {"status":"ALREADY_WHOIS","domain":"baidu.com","registrar":"Beijing Baidu Netcom Science Technology Co., Ltd.","contact_email":"abusecomplaints@markmonitor.com","referral_url":"http://www.markmonitor.com","sponsoring_registrar":"Markmonitor Inc","sponsoring_registrar_eng":"Markmonitor Inc","whois_server":"whois.markmonitor.com","name_server":"dns.baidu.com,ns7.baidu.com,ns3.baidu.com,ns4.baidu.com,ns2.baidu.com","dom_status":"clientupdateprohibited,clienttransferprohibited,clientdeleteprohibited,serverupdateprohibited,servertransferprohibited,serverdeleteprohibited","dom_insdate":"1999-10-11 19:05:17","dom_upddate":"2017-07-27 00:00:00","dom_expdate":"2026-10-11 15:00:00","details":"Domain Name: baidu.com<br>Registry Domain ID: 11181110_DOMAIN_COM-VRSN<br>Registrar WHOIS Server: whois.markmonitor.com<br>Registrar URL: http://www.markmonitor.com<br>Updated Date: 2017-07-27T19:36:28-0700<br>Creation Date: 1999-10-11T04:05:17-0700<br>Registrar Registration Expiration Date: 2026-10-11T00:00:00-0700<br>Registrar: MarkMonitor, Inc.<br>Registrar IANA ID: 292<br>Registrar Abuse Contact Email: abusecomplaints@markmonitor.com<br>Registrar Abuse Contact Phone: +1.2083895740<br>Domain Status: clientUpdateProhibited (https://www.icann.org/epp#clientUpdateProhibited)<br>Domain Status: clientTransferProhibited (https://www.icann.org/epp#clientTransferProhibited)<br>Domain Status: clientDeleteProhibited (https://www.icann.org/epp#clientDeleteProhibited)<br>Domain Status: serverUpdateProhibited (https://www.icann.org/epp#serverUpdateProhibited)<br>Domain Status: serverTransferProhibited (https://www.icann.org/epp#serverTransferProhibited)<br>Domain Status: serverDeleteProhibited (https://www.icann.org/epp#serverDeleteProhibited)<br>Registrant Organization: Beijing Baidu Netcom Science Technology Co., Ltd.<br>Registrant State/Province: Beijing<br>Registrant Country: CN<br>Admin Organization: Beijing Baidu Netcom Science Technology Co., Ltd.<br>Admin State/Province: Beijing<br>Admin Country: CN<br>Tech Organization: Beijing Baidu Netcom Science Technology Co., Ltd.<br>Tech State/Province: Beijing<br>Tech Country: CN<br>Name Server: ns2.baidu.com<br>Name Server: dns.baidu.com<br>Name Server: ns4.baidu.com<br>Name Server: ns7.baidu.com<br>Name Server: ns3.baidu.com<br>DNSSEC: unsigned<br>URL of the ICANN WHOIS Data Problem Reporting System: http://wdprs.internic.net/<br>>>> Last update of WHOIS database: 2018-11-06T06:17:24-0800 <<<<br><br>If certain contact information is not shown for a Registrant, Administrative,<br>or Technical contact, and you wish to send a message to these contacts, please<br>send your message to whoisrelay@markmonitor.com and specify the domain name in<br>the subject line. We will forward that message to the underlying contact.<br><br>If you have a legitimate interest in viewing the non-public WHOIS details, send<br>your request and the reasons for your request to abusecomplaints@markmonitor.com<br>and specify the domain name in the subject line. We will review that request and<br>may ask for supporting documentation and explanation.<br><br>The Data in MarkMonitor.coms WHOIS database is provided by MarkMonitor.com for<br>information purposes, and to assist persons in obtaining information about or<br>related to a domain name registration record.  MarkMonitor.com does not guarantee<br>its accuracy.  By submitting a WHOIS query, you agree that you will use this Data<br>only for lawful purposes and that, under no circumstances will you use this Data to:<br> (1) allow, enable, or otherwise support the transmission of mass unsolicited,<br>     commercial advertising or solicitations via e-mail (spam); or<br> (2) enable high volume, automated, electronic processes that apply to<br>     MarkMonitor.com (or its systems).<br>MarkMonitor.com reserves the right to modify these terms at any time.<br>By submitting this query, you agree to abide by this policy.<br><br>MarkMonitor is the Global Leader in Online Brand Protection.<br><br>MarkMonitor Domain Management(TM)<br>MarkMonitor Brand Protection(TM)<br>MarkMonitor AntiPiracy(TM)<br>MarkMonitor AntiFraud(TM)<br>Professional and Managed Services<br><br>Visit MarkMonitor at http://www.markmonitor.com<br>Contact us at +1.8007459229<br>In Europe, at +44.02032062220<br><br>For more information on Whois status codes, please visit<br> https://www.icann.org/resources/pages/epp-status-codes-2014-06-16-en"}
     */
    private String success;
    private ResultBean result;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "WhoisBean{" +
                "success='" + success + '\'' +
                ", result=" + result +
                '}';
    }
}
