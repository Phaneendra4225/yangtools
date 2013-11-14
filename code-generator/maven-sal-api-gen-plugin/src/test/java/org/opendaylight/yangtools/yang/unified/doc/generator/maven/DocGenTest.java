package org.opendaylight.yangtools.yang.unified.doc.generator.maven;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.opendaylight.yangtools.sal.binding.generator.api.BindingGenerator;
import org.opendaylight.yangtools.sal.binding.generator.impl.BindingGeneratorImpl;
import org.opendaylight.yangtools.yang.model.api.Module;
import org.opendaylight.yangtools.yang.model.api.SchemaContext;
import org.opendaylight.yangtools.yang.parser.impl.YangParserImpl;
import org.opendaylight.yangtools.yang2sources.spi.CodeGenerator;

public class DocGenTest {

    public static final String FS = File.separator;
    static final String BASE_PKG = "org.opendaylight.yang.gen.v1";

    static final String TEST_PATH = "target" + FS + "test";
    static final File TEST_DIR = new File(TEST_PATH);

    static final File GENERATOR_OUTPUT_DIR = new File(TEST_PATH);
    static final String COMPILER_OUTPUT_PATH = TEST_PATH + FS + "bin";
    static final File COMPILER_OUTPUT_DIR = new File(COMPILER_OUTPUT_PATH);


    protected YangParserImpl parser;
    protected BindingGenerator bindingGenerator;

    @BeforeClass
    public static void createTestDirs() {
        if (TEST_DIR.exists()) {
            deleteTestDir(TEST_DIR);
        }
        assertTrue(GENERATOR_OUTPUT_DIR.mkdirs());
        assertTrue(COMPILER_OUTPUT_DIR.mkdirs());
    }

    @Before
    public void init() {
        parser = new YangParserImpl();
        bindingGenerator = new BindingGeneratorImpl();
    }

    @Test
    public void testListGeneration() throws Exception {
        final List<File> sourceFiles = getSourceFiles("/doc-gen");
        final Set<Module> modulesToBuild = parser.parseYangModels(sourceFiles);
        final SchemaContext context = parser.resolveSchemaContext(modulesToBuild);
        final CodeGenerator generator = new DocumentationGeneratorImpl();
        generator.generateSources(context, GENERATOR_OUTPUT_DIR, modulesToBuild);


    }

    static List<File> getSourceFiles(String path) throws FileNotFoundException {
        final String resPath = DocGenTest.class.getResource(path).getPath();
        final File sourcesDir = new File(resPath);
        if (sourcesDir.exists()) {
            final List<File> sourceFiles = new ArrayList<>();
            final File[] fileArray = sourcesDir.listFiles();
            if (fileArray == null) {
                throw new IllegalArgumentException("Unable to locate files in " + sourcesDir);
            }
            sourceFiles.addAll(Arrays.asList(fileArray));
            return sourceFiles;
        } else {
            throw new FileNotFoundException("Testing files were not found(" + sourcesDir.getName() + ")");
        }
    }


    static void deleteTestDir(File file) {
        if (file.isDirectory()) {
            File[] filesToDelete = file.listFiles();
            if (filesToDelete != null) {
                for (File f : filesToDelete) {
                    deleteTestDir(f);
                }
            }
        }
        if (!file.delete()) {
            throw new RuntimeException("Failed to clean up after test");
        }
    }
}
