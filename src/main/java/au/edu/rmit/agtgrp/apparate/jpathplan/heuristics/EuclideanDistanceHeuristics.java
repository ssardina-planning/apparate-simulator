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

package au.edu.rmit.agtgrp.apparate.jpathplan.heuristics;

import au.edu.rmit.agtgrp.apparate.jpathplan.entites.State;
import au.edu.rmit.agtgrp.apparate.jpathplan.entites.SearchDomain;

/**
 * An implementation of {@link DistanceHeuristics} based on Euclidean distance
 * between any two points in a two dimensional space. This is a Singleton class.
 * 
 * @author Abhijeet Anand (<a href="mailto:abhijeet.anand@rmit.edu.au">abhijeet
 *         [dot] anand [at] rmit [dot] edu [dot] au</a>)
 * 
 */
public class EuclideanDistanceHeuristics implements DistanceHeuristics {
    
    private static EuclideanDistanceHeuristics _instance = null;
    
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
    protected EuclideanDistanceHeuristics() {
        // Just an empty constructor as at the time of first creation of the
        // class. This could later be extended/modified to provide proper
        // initialisation of the class, if necessary.
    }
    
    /*
     * =======================================================================*
     * ---------------------------- STATIC METHODS ---------------------------*
     * =======================================================================*
     */
    /**
     * Returns a reference to the singleton object of this class.
     * 
     * @return {@link EuclideanDistanceHeuristics}
     */
    public static EuclideanDistanceHeuristics instance() {
        if (_instance == null) {
            _instance = new EuclideanDistanceHeuristics();
        }
        return _instance;
    }
    
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
     * (non-Javadoc)
     * @see
     * DistanceHeuristics
     * #h(SearchDomain,
     * au.rmit.ract.planning.pathplanning.entity.Node,
     * au.rmit.ract.planning.pathplanning.entity.Node)
     */
    @Override
    public float h(SearchDomain map, State cNode, State gNode) {
        // TODO Auto-generated method stub
        return 0;
    }
    
    /*
     * (non-Javadoc)
     * @see
     * DistanceHeuristics#updateH(au.edu
     * .rmit.cs.ract.planning.pathplanning.entity.SearchDomain,
     * au.rmit.ract.planning.pathplanning.entity.Node,
     * au.rmit.ract.planning.pathplanning.entity.Node, float)
     */
    @Override
    public boolean updateH(SearchDomain map, State cNode, State gNode, float hModifier) {
        // TODO Auto-generated method stub
        return false;
    }
    
    /*
     * =======================================================================*
     * --------------------------- UTILITY METHODS ---------------------------*
     * =======================================================================*
     */

}
