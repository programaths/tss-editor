package be.programaths.idea.plugins.editors.tss

import be.programaths.idea.plugins.editors.tss.language.tssLanguage
import com.intellij.openapi.fileTypes.LanguageFileType
import com.intellij.openapi.util.IconLoader
import javax.swing.Icon


object tssFileType: LanguageFileType(tssLanguage) {
    val EXT = "tss"

    override fun getIcon(): Icon? = IconLoader.getIcon("/icons/icon_16.png")

    override fun getName(): String = "TSS editor"

    override fun getDefaultExtension(): String = EXT

    override fun getDescription(): String = "Permit to edit Templating Style Sheet files used by phptransphorm"
}