package com.example.familyproj.test.facade;


import com.example.familyproj.test.abstractfactory.AbstractFamilyMembersFactory;
import com.example.familyproj.test.entity.Family;
import com.example.familyproj.test.memento.FamilyRegistration;
import com.example.familyproj.test.memento.FamilyState;

public class FamilyFacade {

    private Report report;

    private FamilyRegistration reg;

    private AbstractFamilyMembersFactory creator;
    {
        reg = new FamilyRegistration();
        creator = new AbstractFamilyMembersFactory();
    }
    public FamilyRegistration getFamilyReg(){
        return reg;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public void report() {

    }

    public FamilyState findFamilyState(Family family) {
            return reg.restore(family);
    }

    public boolean registrateFamily(FamilyState family) {
            return reg.add(family);
    }



    public Family createFamily(boolean father, boolean mother,
                               int howManyChildren){
        Family family = new Family();
        if (mother || father) {
            if (mother)
                family.addMember(creator.createMother());
            if (father)
                family.addMember(creator.createFather());
            for (int i = 0; i < howManyChildren; i++) {
                family.addMember(creator.createChild());
            }
            return family;
        }
        return family;
    }

}