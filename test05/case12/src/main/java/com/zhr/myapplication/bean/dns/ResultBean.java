package com.zhr.myapplication.bean.dns;

import com.alibaba.fastjson.annotation.JSONField;

public class ResultBean {

    /**
     * domain : baidu.com
     * dns_server : 223.5.5.5
     * name_server : ns2.baidu.com,ns4.baidu.com,ns7.baidu.com,dns.baidu.com,ns3.baidu.com
     *
     * 域名 DNS服务器 名称服务器
     */
    private String domain;
    @JSONField(name = "dns_server")
    private String dnsServer;
    @JSONField(name = "name_server")
    private String nameServer;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDnsServer() {
        return dnsServer;
    }

    public void setDnsServer(String dnsServer) {
        this.dnsServer = dnsServer;
    }

    public String getNameServer() {
        return nameServer;
    }

    public void setNameServer(String nameServer) {
        this.nameServer = nameServer;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "domain='" + domain + '\'' +
                ", dnsServer='" + dnsServer + '\'' +
                ", nameServer='" + nameServer + '\'' +
                '}';
    }
}
