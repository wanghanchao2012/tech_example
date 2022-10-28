 由于springboot扫描类的默认方式只能扫描到 **Application 同级别及其子文件夹下的文件
 所以引用类的路径高于 **Application文件时或不在同一包名下或第三方jar包时，
 需要手动引入并使spring能扫描到，此时需要用到@Import
 **Application
 *
 @Import 可以是一个普通的java bean 也可以是使用@Configuration修饰过的类
 @Import 注解通常在**Application类上或@Configuration修饰的类上使用
 <p>
 <p>
 导入的实现方式有三种
 1.@Import方式
 2.实现ImportSelector接口，这种方式通常是做成一个jar包共第三方使用
 3.实现ImportBeanDefinitionRegistrar接口， register后可以直接使用实体