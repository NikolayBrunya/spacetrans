package com.company.spacetrans.screen.company;

import io.jmix.ui.screen.*;
import com.company.spacetrans.entity.Company;

@UiController("st_Company.browse")
@UiDescriptor("company-browse.xml")
@LookupComponent("companiesTable")
public class CompanyBrowse extends StandardLookup<Company> {
}