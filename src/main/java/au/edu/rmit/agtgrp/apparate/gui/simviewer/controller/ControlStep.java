/*
 * APPARATE - Path Planner Framework and Simulator in Java
 *
 * Copyright (C) 2010-2018 Andy Xie, Abhijeet Anand and Sebastian Sardina
 * School of CS and IT, RMIT University, Melbourne VIC 3000.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package au.edu.rmit.agtgrp.apparate.gui.simviewer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import au.edu.rmit.agtgrp.apparate.gui.simviewer.model.timer.ThreadedTimer;


/**
 * Enables to step the environment execution
 * 
 * @author Andy Heng Xie
 *
 */

public class ControlStep implements ActionListener {


	/* *******************
	 * Constructor
	 *********************/
	
	public ControlStep() {
	}
	

	/* *******************
	 * Events
	 *********************/

	/**
	 * Pause the run when uncheck, resume when checked
	 */

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			(new ThreadedTimer(Launcher.getRunEnviron().getTimer())).Step();
		} catch (Exception e) { }
	}
}
	