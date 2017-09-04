package com.huffman;

import java.io.File;
import java.util.HashMap;

/**
 * @author André Victor
 * @author Khadije El Zein
 */
public abstract class Leitura {
    protected HashMap frequencias;

    abstract public HashMap extraiFrequenciaArquivo(File arquivo);

    public HashMap getFrequencias() {
        return frequencias;
    }

}
