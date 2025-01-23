package com.wsgc.rule.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class DroolsConfig {

//    private KieServices kieServices = KieServices.Factory.get();
//
//    private KieFileSystem getKieFileSystem() throws IOException
//    {
//        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
//        kieFileSystem.write(ResourceFactory.newClassPathResource("rules/employee_incentive.drl"));
//        return kieFileSystem;
//    }
//
//    @Bean
//    public KieContainer getKieContainer() throws IOException
//    {
//        System.out.println("KIE Container Created...");
//        getKieRepository();
//        KieBuilder kieBuilder = kieServices.newKieBuilder(getKieFileSystem());
//        kieBuilder.buildAll();
//        KieModule kieModule = kieBuilder.getKieModule();
//        KieContainer kieContainer = kieServices.newKieContainer(kieModule.getReleaseId());
//        return kieContainer;
//    }
//
//    private void getKieRepository()
//    {
//        final KieRepository kieRepository = kieServices.getRepository();
//        kieRepository.addKieModule(
//                new KieModule() {
//                    @Override
//                    public ReleaseId getReleaseId() {
//                        return kieRepository.getDefaultReleaseId();
//                    }
//                }
//        );
//    }
//
//    @Bean
//    public KieSession getKieSession() throws IOException
//    {
//        System.out.println("KIE Session Created...");
//        return getKieContainer().newKieSession();
//    }
}
