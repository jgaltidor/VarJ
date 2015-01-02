///////////////////////////////////////////////////////////////////////////////
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this program; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
///////////////////////////////////////////////////////////////////////////////
package gnu.trove.generate;

import java.io.*;
import java.util.regex.Pattern;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * Generates classes that are created from templates. The "*.template" files must be in
 * the classpath (because Class.getResource() is used to load the files).
 */
public class Generate {
    private static final String P2P_MAP_DECORATOR_NAME = "P2PMapDecorator.template";
    private static final String DECORATORS_CLASS_TEMP = "Decorators.template";
    private static final WrapperInfo[] WRAPPERS = new WrapperInfo[] {
        new WrapperInfo( "double", "Double", "POSITIVE_INFINITY", "NEGATIVE_INFINITY" ),
        new WrapperInfo( "float", "Float", "POSITIVE_INFINITY", "NEGATIVE_INFINITY" ),
        new WrapperInfo( "int", "Integer", "MAX_VALUE", "MIN_VALUE" ),
        new WrapperInfo( "long", "Long", "MAX_VALUE", "MIN_VALUE" ),
        new WrapperInfo( "byte", "Byte", "MAX_VALUE", "MIN_VALUE" ),
        new WrapperInfo( "short", "Short", "MAX_VALUE", "MIN_VALUE" ) };


    public static void main(String[] args) throws IOException {
        if ( args.length == 0 ) {
            System.out.println( "Usage: Generate <input_path> <output_path>" );
            return;
        }

        File input_path = new File(args[ 0 ]);
        File output_path = new File(args[ 1 ]);
        System.out.println("File: " + output_path.getAbsolutePath());
        if (!output_path.exists()) {
            System.err.println("\"" + output_path + "\" does not exist");
            return;
        }


        generateP2PMapDecorators(input_path,output_path);

        // Decorators
        generate("P2OMapDecorator.template", "decorator/T", "ObjectHashMapDecorator.java", input_path, output_path);
        generate("O2PMapDecorator.template", "decorator/TObject", "HashMapDecorator.java", input_path, output_path);
        generate("PHashSetDecorator.template", "decorator/T", "HashSetDecorator.java", input_path, output_path);
        generateDecoratorsClass(input_path, output_path);

        // Iterators
        generate("O2PIterator.template", "TObject", "Iterator.java", input_path, output_path);
        generate("P2OIterator.template", "T", "ObjectIterator.java", input_path, output_path);
        generateP2P("P2PIterator.template", "T", "Iterator.java", input_path, output_path);
        generate("PIterator.template", "T", "Iterator.java", input_path, output_path);

        // Procedures
        generate("O2PProcedure.template", "TObject", "Procedure.java", input_path, output_path);
        generate("P2OProcedure.template", "T", "ObjectProcedure.java", input_path, output_path);
        generateP2P("P2PProcedure.template", "T", "Procedure.java", input_path, output_path);
        generate("PProcedure.template", "T", "Procedure.java", input_path, output_path);

        // Functions
        generate("PFunction.template", "T", "Function.java", input_path, output_path);

        // Hashing Strategy interfaces
        generate("PHashingStrategy.template", "T", "HashingStrategy.java", input_path, output_path);

        // Hash abstract classes
        generate("PHash.template", "T", "Hash.java", input_path, output_path);

        // HashMaps
        generate("P2OHashMap.template", "T", "ObjectHashMap.java", input_path, output_path);
        generate("O2PHashMap.template", "TObject", "HashMap.java", input_path, output_path);
        generateP2P("P2PHashMap.template", "T", "HashMap.java", input_path, output_path);

        // ArrayLists
        generate( "PArrayList.template", "T", "ArrayList.java", input_path, output_path);

        // HashSets
        generate( "PHashSet.template", "T", "HashSet.java", input_path, output_path);

        // Stacks
        generate( "PStack.template", "T", "Stack.java", input_path, output_path);


        System.out.println( "Generation complete." );
    }

    private static void generate(String templateName, String pathPrefix, String pathSuffix,
        File input_path, File output_path) throws IOException {

        String template = readFile(templateName, input_path);
        for (int i = 0; i < WRAPPERS.length; i++) {
            WrapperInfo info = WRAPPERS[ i ];

            String e = info.primitive;
            String ET = info.class_name;
            String E = shortInt(ET);
            String EMAX = info.max_value;
            String EMIN = info.min_value;
            String out = template;

            out = Pattern.compile("#e#").matcher(out).replaceAll(e);
            out = Pattern.compile("#E#").matcher(out).replaceAll(E);
            out = Pattern.compile("#ET#").matcher(out).replaceAll(ET);
            out = Pattern.compile("#EMAX#").matcher(out).replaceAll(EMAX);
            out = Pattern.compile("#EMIN#").matcher(out).replaceAll(EMIN);

            String outFile = pathPrefix + E + pathSuffix;
            writeFile(outFile, out, output_path);
        }
    }

    private static void generateP2P(String templateName, String pathPrefix,
        String pathSuffix, File input_path, File output_path) throws IOException {

        String template = readFile(templateName, input_path);
        for (int i = 0; i < WRAPPERS.length; i++) {
            WrapperInfo info = WRAPPERS[ i ];

            String e = info.primitive;
            String ET = info.class_name;
            String E = shortInt(ET);
            String EMAX = info.max_value;
            String EMIN = info.min_value;

            for (int j = 0; j < WRAPPERS.length; j++) {
                String out = template;
                out = Pattern.compile("#e#").matcher(out).replaceAll(e);
                out = Pattern.compile("#E#").matcher(out).replaceAll(E);
                out = Pattern.compile("#ET#").matcher(out).replaceAll(ET);
                out = Pattern.compile("#EMAX#").matcher(out).replaceAll(EMAX);
                out = Pattern.compile("#EMIN#").matcher(out).replaceAll(EMIN);

                WrapperInfo jinfo = WRAPPERS[ j ];

                String f = jinfo.primitive;
                String FT = jinfo.class_name;
                String F = shortInt(FT);
                String FMAX = jinfo.max_value;
                String FMIN = jinfo.min_value;

                out = Pattern.compile("#f#").matcher(out).replaceAll(f);
                out = Pattern.compile("#F#").matcher(out).replaceAll(F);
                out = Pattern.compile("#FT#").matcher(out).replaceAll(FT);
                out = Pattern.compile("#FMAX#").matcher(out).replaceAll(FMAX);
                out = Pattern.compile("#FMIN#").matcher(out).replaceAll(FMIN);

                String outFile = pathPrefix + E + F + pathSuffix;
                writeFile(outFile, out, output_path);
            }
        }
    }

    private static void generateP2PMapDecorators(File input_path, File output_path)
        throws IOException {
        String template = readFile(P2P_MAP_DECORATOR_NAME, input_path);
        for (int i = 0; i < WRAPPERS.length; i++) {
            WrapperInfo info = WRAPPERS[ i ];

            String k = info.primitive;
            String KT = info.class_name;
            String K = shortInt(KT);
            String KMAX = info.max_value;
            String KMIN = info.min_value;

            for (int j = 0; j < WRAPPERS.length; j++) {
                WrapperInfo jinfo = WRAPPERS[ j ];

                String v = jinfo.primitive;
                String VT = jinfo.class_name;
                String V = shortInt(VT);
                String VMAX = jinfo.max_value;
                String VMIN = jinfo.min_value;

                String out = template;

                out = Pattern.compile("#v#").matcher(out).replaceAll(v);
                out = Pattern.compile("#V#").matcher(out).replaceAll(V);
                out = Pattern.compile("#k#").matcher(out).replaceAll(k);
                out = Pattern.compile("#K#").matcher(out).replaceAll(K);
                out = Pattern.compile("#KT#").matcher(out).replaceAll(KT);
                out = Pattern.compile("#VT#").matcher(out).replaceAll(VT);
                out = Pattern.compile("#KMAX#").matcher(out).replaceAll(KMAX);
                out = Pattern.compile("#VMAX#").matcher(out).replaceAll(VMAX);
                out = Pattern.compile("#KMIN#").matcher(out).replaceAll(KMIN);
                out = Pattern.compile("#VMIN#").matcher(out).replaceAll(VMIN);
                String outFile = "decorator/T" + K + V + "HashMapDecorator.java";
                writeFile(outFile, out, output_path);
            }
        }
    }

    private static void generateDecoratorsClass(File input_path, File output_path)
        throws IOException {

        String raw_template = readFile(DECORATORS_CLASS_TEMP, input_path);

        // NOTE: don't try to use trove here... it ain't built yet! :-)
        Map<Integer, String> replicated_content = new HashMap<Integer, String>();

        // Find the replicated content blocks in the template. For ease, read this line
        // by line.
        String processed_template = null;
        BufferedReader reader = new BufferedReader(new StringReader(raw_template));
        String line;
        StringBuilder buffer = new StringBuilder();
        boolean in_replicated_block = false;
        boolean need_newline = false;
        while ((line = reader.readLine()) != null) {
            if (!in_replicated_block &&
                line.startsWith("====START_REPLICATED_CONTENT #")) {

                in_replicated_block = true;
                need_newline = false;

                if (processed_template == null) {
                    processed_template = buffer.toString();
                }

                buffer = new StringBuilder();
            }
            else if (in_replicated_block &&
                line.startsWith("=====END_REPLICATED_CONTENT #")) {
                int number_start_index = "=====END_REPLICATED_CONTENT #".length();
                int number_end_index = line.indexOf("=", number_start_index);

                String number = line.substring(number_start_index, number_end_index);
                Integer number_obj = new Integer(number);

                replicated_content.put(number_obj, buffer.toString());

                in_replicated_block = false;
                need_newline = false;
            }
            else {
                if (need_newline) {
                    buffer.append(System.getProperty("line.separator"));
                }
                else need_newline = true;

                buffer.append(line);
            }
        }

        for (Map.Entry<Integer, String> entry : replicated_content.entrySet()) {
            // Replace the markers in the replicated content

            StringBuilder entry_buffer = new StringBuilder();

            boolean first_loop = true;
            for (int i = 0; i < WRAPPERS.length; i++) {
                WrapperInfo info = WRAPPERS[ i ];

                String k = info.primitive;
                String KT = info.class_name;
                String K = shortInt(KT);
                String KMAX = info.max_value;
                String KMIN = info.min_value;

                for (int j = 0; j < WRAPPERS.length; j++) {
                    WrapperInfo jinfo = WRAPPERS[ j ];

                    String v = jinfo.primitive;
                    String VT = jinfo.class_name;
                    String V = shortInt(VT);
                    String VMAX = jinfo.max_value;
                    String VMIN = jinfo.min_value;

                    String out = entry.getValue();
                    String before_e = out;
                    out = Pattern.compile("#e#").matcher(out).replaceAll(k);
                    out = Pattern.compile("#E#").matcher(out).replaceAll(K);
                    out = Pattern.compile("#ET#").matcher(out).replaceAll(KT);
                    out = Pattern.compile("#EMAX#").matcher(out).replaceAll(KMAX);
                    out = Pattern.compile("#EMIN#").matcher(out).replaceAll(KMIN);
                    boolean uses_e = !out.equals(before_e);

                    // If we use "e" (instead of "k" & "v", then we don't need the inner
                    // map. Yeah, this is ugly I know... but it works.
                    if (uses_e && j != 0) break;

                    out = Pattern.compile("#v#").matcher(out).replaceAll(v);
                    out = Pattern.compile("#V#").matcher(out).replaceAll(V);
                    out = Pattern.compile("#VT#").matcher(out).replaceAll(VT);
                    out = Pattern.compile("#VMAX#").matcher(out).replaceAll(VMAX);
                    out = Pattern.compile("#VMIN#").matcher(out).replaceAll(VMIN);

                    out = Pattern.compile("#k#").matcher(out).replaceAll(k);
                    out = Pattern.compile("#K#").matcher(out).replaceAll(K);
                    out = Pattern.compile("#KT#").matcher(out).replaceAll(KT);
                    out = Pattern.compile("#KMAX#").matcher(out).replaceAll(KMAX);
                    out = Pattern.compile("#KMIN#").matcher(out).replaceAll(KMIN);

                    if (first_loop) first_loop = false;
                    else {
                        entry_buffer.append(System.getProperty("line.separator"));
                        entry_buffer.append(System.getProperty("line.separator"));
                    }

                    entry_buffer.append(out);
                }
            }

            processed_template =
                Pattern.compile("#REPLICATED" + entry.getKey() + "#").matcher(
                    processed_template).replaceAll(entry_buffer.toString());
        }

        writeFile("Decorators.java", processed_template, output_path);
    }

    private static void writeFile(String file, String out, File output_path)
        throws IOException {

        File destination = new File(output_path.getPath() + "/" + file);
        File parent = destination.getParentFile();
        parent.mkdirs();

        // Write to a temporary file
        File temp = File.createTempFile("trove", "gentemp", new File("output"));
        FileWriter writer = new FileWriter(temp);
        writer.write(out);
        writer.close();


        // Now determine if it should be moved to the final location
        final boolean need_to_move;
        if (destination.exists()) {
            boolean matches;
            try {
                MessageDigest digest = MessageDigest.getInstance("MD5");

                byte[] current_file = digest(destination, digest);
                byte[] new_file = digest(temp, digest);

                matches = Arrays.equals(current_file, new_file);
            }
            catch (NoSuchAlgorithmException ex) {
                System.err.println("WARNING: Couldn't load digest algorithm to compare " +
                    "new and old template. Generation will be forced." );
                matches = false;
            }

            need_to_move = !matches;
        }
        else need_to_move = true;


        // Now move it if we need to move it
        if (need_to_move) {
            destination.delete();
            if (!temp.renameTo(destination)) {
                throw new IOException("ERROR writing: " + destination);
            }
            else System.out.println("Wrote: " + destination);
        }
        else {
            System.out.println("Skipped: " + destination);
            temp.delete();
        }
    }


    private static byte[] digest(File file, MessageDigest digest) throws IOException {
        digest.reset();

        byte[] buffer = new byte[1024];
        FileInputStream in = new FileInputStream(file);
        try {
            int read = in.read(buffer);
            while (read >= 0) {
                digest.update(buffer, 0, read);

                read = in.read(buffer);
            }

            return digest.digest();
        }
        finally {
            try {
                in.close();
            }
            catch (IOException ex) {
                // ignore
            }
        }
    }


    private static String shortInt(String type) {
        return type.equals("Integer") ? "Int" : type;
    }

    private static String readFile(String name, File input_path) throws IOException {
        File file = new File( input_path, name );

        if ( !file.exists() ) {
            throw new NullPointerException("Couldn't find: " + file);
        }

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            StringBuilder out = new StringBuilder();

            while (true) {
                String line = reader.readLine();
                if (line == null) break;
                out.append(line);
                out.append("\n");
            }
            return out.toString();
        }
        finally {
            if ( reader != null ) {
                try {
                    reader.close();
                }
                catch( IOException ex ) {
                    // ignore
                }
            }
        }
    }


    private static class WrapperInfo {
        final String primitive;
        final String class_name;
        final String max_value;
        final String min_value;

        WrapperInfo(String primitive, String class_name, String max_value,
            String min_value) {

            this.primitive = primitive;
            this.class_name = class_name;
            this.max_value = class_name + "." + max_value;
            this.min_value = class_name + "." + min_value;
        }
    }
}
