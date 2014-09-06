package com.radcortez.wow.auctions.batch.business;

import com.radcortez.wow.auctions.business.WoWBusiness;
import com.radcortez.wow.auctions.business.WoWBusinessBean;
import com.radcortez.wow.auctions.entity.Realm;

import javax.enterprise.inject.Alternative;
import javax.inject.Named;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author Roberto Cortez
 */
@Named
@Alternative
public class WoWBusinessAlternative extends WoWBusinessBean implements WoWBusiness {
    @Override
    public List<Realm> findRealmsByRegion(Realm.Region region) {
        return super.findRealmsByRegion(region).stream().limit(5).collect(toList());
    }
}
