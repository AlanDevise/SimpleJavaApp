package JavaCoreConception.Chapter03;

import java.util.StringJoiner;

/**
 * @Filename: ShadowCloneTest.java
 * @Package: JavaCoreConception.Chapter03
 * @Version: V1.0.0
 * @Description: 1. 浅/深拷贝测试
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2023年02月26日 14:46
 */

public class ShadowCloneTest implements Cloneable {

    public static void main(String[] args) throws CloneNotSupportedException {
        Address address = new Address();
        address.setProvince("Zhejiang");

        ShadowCloneTest person1 = new ShadowCloneTest("Alan", address);
        ShadowCloneTest person2 = (ShadowCloneTest)person1.clone();
        person2.getAddress().setProvince("JiangSu");

        System.out.println(person1);
        System.out.println(person2);
    }

    private String name;

    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ShadowCloneTest(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return new StringJoiner(",",ShadowCloneTest.class.getSimpleName()+"[","]")
                .add("name='"+name+"'")
                .add("address="+address)
                .toString();
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        ShadowCloneTest person = (ShadowCloneTest) super.clone();
        person.setAddress((Address) address.clone());
        return person;
    }
}

class Address implements Cloneable{
    private String province;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", Address.class.getSimpleName() + "[", "]")
                .add("province='" + province + "'")
                .toString();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
