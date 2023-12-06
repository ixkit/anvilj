package org.ixkit.anvilj.runtime;



import org.ixkit.things.equipment.controller.EquipmentController;
import org.ixkit.things.twinscenario.controller.TwinScenarioController;

public class Loader {

    private EquipmentController controller;

    private TwinScenarioController scenarioController;

    public Loader(EquipmentController controller) {
        this.controller = controller;

    }
}
