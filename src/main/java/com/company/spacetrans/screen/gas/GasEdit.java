package com.company.spacetrans.screen.gas;

import io.jmix.ui.screen.*;
import com.company.spacetrans.entity.Gas;

@UiController("st_Gas.edit")
@UiDescriptor("gas-edit.xml")
@EditedEntityContainer("gasDc")
public class GasEdit extends StandardEditor<Gas> {
}