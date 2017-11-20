package be.programaths.idea.plugins.editors.tss

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import com.intellij.openapi.util.IconLoader
import javax.swing.Icon

class TSSColorSettingsPage : ColorSettingsPage {
    val DESCRIPTORS = arrayOf(
            AttributesDescriptor("Element",TSSSyntaxHighlighter.ELEMENT),
            AttributesDescriptor("Class",TSSSyntaxHighlighter.CLASS),
            AttributesDescriptor("Comment",TSSSyntaxHighlighter.COMMENT),
            AttributesDescriptor("Function",TSSSyntaxHighlighter.FUNC),
            AttributesDescriptor("Id",TSSSyntaxHighlighter.ID),
            AttributesDescriptor("Separator",TSSSyntaxHighlighter.SEP),
            AttributesDescriptor("String",TSSSyntaxHighlighter.STRING)
    )

    override fun getHighlighter(): SyntaxHighlighter = TSSSyntaxHighlighter()

    override fun getAdditionalHighlightingTagToDescriptorMap(): MutableMap<String, TextAttributesKey>? = null

    override fun getIcon(): Icon? = IconLoader.getIcon("/icons/icon_16.png")

    override fun getAttributeDescriptors(): Array<AttributesDescriptor> = DESCRIPTORS

    override fun getColorDescriptors(): Array<ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY

    override fun getDisplayName(): String = "TSS"

    override fun getDemoText(): String = """
        /* Selector makes no sense, it's ok for a demo */
        div .foo > #someId {
            content: "item" , repeat(path.to.variable);
        }
    """.trimIndent()
}