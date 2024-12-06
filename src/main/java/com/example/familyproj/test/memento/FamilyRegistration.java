package com.example.familyproj.test.memento;


import com.example.familyproj.test.entity.Family;
import com.example.familyproj.test.observer.NewFamilyNotifier;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FamilyRegistration {
    private int id;
    private int date;
    private List<FamilyState> regHistory;

    private NewFamilyNotifier notifier;
    {
        regHistory = new ArrayList<>();
        notifier = new NewFamilyNotifier();
    }

    public List<FamilyState> getRegHistory() {
        return regHistory;
    }

    public boolean add(FamilyState family) {
        if (family != null ) {
            regHistory.add(family);
            return true;
        }
        return false;
    }

    public FamilyState restore(Family family) {
        FamilyState f = new FamilyState();
        for (int i = 0; i < regHistory.size(); i++) {
            f.saveState(family);
            if (family.equals(f))
                return regHistory.get(i);
        }
        return null;
    }

   /* public void subscribeToFamilyNews(FamilyState f) {
        if (f != null)
            notifier.addToNotifier(f);
    }*/

}