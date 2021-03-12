@echo off
    if %1% == knit goto KnitToPdf
    if %1% == test goto CoreTester
    if %1% == generate-template goto GenerateTemplate
    if %1% == edit-template goto EditTemplateOnline
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
        if not exist tests_log mkdir tests_log
        kotlinc Main.kt -d build && cd build && kotlin MainKt test && cd..
        goto End

    :GenerateTemplate
        echo Generating markdown template....
        if not exist templates mkdir templates
        kotlinc Main.kt -d build && cd build && kotlin MainKt generate-template %2 && cd..
        goto End

    :EditTemplateOnline
        :: open website for editing markdown + live preview of non executable parts
        start "" https://stackedit.io/app#
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