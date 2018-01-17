/**
 * A Library of Path Planning Algorithms
 *
 * Copyright (C) 2010 Abhijeet Anand and Sebastian Sardina, School of CS and IT, RMIT University, Melbourne VIC 3000.
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
package au.edu.rmit.agtgrp.apparate.jpathplan.heuristics;

/**
 * An enumeration which defines the types of Heuristics for which a default implementation has been
 * provided within the library.
 * 
 * @author Abhijeet Anand (<a href="mailto:abhijeet.anand@rmit.edu.au">abhijeet [dot] anand [at]
 *         rmit [dot] edu [dot] au</a>)
 * 
 */
public enum HeuristicsType {
    
    /*
     * =======================================================================*
     * ----------------------------- INNER CLASS -----------------------------*
     * =======================================================================*
     */
    MANHATTAN_DISTANCE {
        
        @Override
        public Heuristics instance() {
            return ManhattanDistanceHeuristics.instance();
        }
        
    },
    
    EUCLIDEAN_DISTANCE {
        
        @Override
        public Heuristics instance() {
            return EuclideanDistanceHeuristics.instance();
        }
        
    },
    ;
    
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

    /*
     * =======================================================================*
     * --------------------------- ACCESSOR METHODS --------------------------*
     * =======================================================================*
     */
    public abstract Heuristics instance();
    
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
