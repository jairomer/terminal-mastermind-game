package com.master.upm.Mastermind;

import java.util.ArrayList;

class Code {
    protected Color colors[];
    protected int length;

    protected boolean colorInList (Color color, ArrayList<Color> list) {
        for (Color c : list) {
            if (c == color)
                return true;
        }
        return false;
    }

    public ArrayList<Color> getColors() {
        ArrayList<Color> clrs = new ArrayList();
        for (int i=0; i<this.length; ++i)
            clrs.add(this.colors[i]);
        return clrs;
    }

    public boolean equals(Code c) {
        if (c.getColors().size() != this.length)
            return false;

        int i=0;
        for (Color col: c.getColors()) {
            if (col != this.colors[i])
                return false;
            i++;
        }

        return true;
    }

    public int getLength() {
        return this.length;
    }
}
