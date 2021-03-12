@echo off
    if %1% == knit goto KnitToPdf
    if %1% == test goto CoreTester
    if %1% == generate-template goto GenerateTemplate
    if %1% == javac goto CompileJavaClasses
    goto End

    :KnitToPdf
        if %2. ==. (
            echo Missing markdown file input
            goto End
        )
        echo Generating Pdf....
        goto End

    :CoreTester
        echo Testing JSM Core....
        kotlinc Main.kt -d build && cd build && kotlin MainKt test && cd..
        goto End

    :GenerateTemplate
        echo Generating markdown template....
        goto End
    
    :CompileJavaClasses
        echo Deleting data from build...
        del /S build
        echo Compiling all .java files
        for %%f in (core/*.java*) do (
            javac -d build core/%%f
            echo Compiling %%f
        ) 
        echo Succesfully compiled....
        goto End

    :End
PAUSE