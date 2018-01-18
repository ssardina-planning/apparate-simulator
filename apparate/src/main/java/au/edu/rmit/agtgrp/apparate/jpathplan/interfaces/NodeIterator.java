/*
 * APPARATE - Path Planner Framework and Simulator in Java
 *
 * Copyright (C) 2010-2018
 * Andy Xie, Abhijeet Anand and Sebastian Sardina
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

package au.edu.rmit.agtgrp.apparate.jpathplan.interfaces;

import au.edu.rmit.agtgrp.apparate.jpathplan.entites.State;

/**
 * @author Abhijeet Anand (<a href="mailto:abhijeet.anand@rmit.edu.au">abhijeet [dot] anand [at] rmit [dot] edu [dot] au</a>)
 *
 */
public interface NodeIterator {
    


    /*
     * =======================================================================*
     * ----------------------------- INNER CLASS -----------------------------*
     * =======================================================================*
     */
    
    /*
     * =======================================================================*
     * ----------------------------- CONSTRUCTORS ----------------------------*
     * =======================================================================*
     */

    /*
     * =======================================================================*
     * ---------------------------- STATIC METHODS ---------------------------*
     * =======================================================================*
     */

    /*
     * =======================================================================*
     * ---------------------------- PUBLIC METHODS ---------------------------*
     * =======================================================================*
     */
    /**
     * Return the next element in the sequence
     *
     * @return the next state in the sequence
     */
    public State next();
    
    
    /**
     * Is the sequence at the end?
     *
     * @return true if sequence is finished
     */
    public boolean eoi();
    /*
     * =======================================================================*
     * --------------------------- ACCESSOR METHODS --------------------------*
     * =======================================================================*
     */

    /*
     * =======================================================================*
     * --------------------------- MUTATOR METHODS ---------------------------*
     * =======================================================================*
     */

    /*
     * =======================================================================*
     * --------------------- OVERRIDDEN INTERFACE METHODS --------------------*
     * =======================================================================*
     */

    /*
     * =======================================================================*
     * --------------------------- UTILITY METHODS ---------------------------*
     * =======================================================================*
     */
    
}