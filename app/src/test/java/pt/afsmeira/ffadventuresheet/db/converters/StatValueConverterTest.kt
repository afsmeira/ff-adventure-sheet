package pt.afsmeira.ffadventuresheet.db.converters

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import pt.afsmeira.ffadventuresheet.model.Stat

@RunWith(JUnit4::class)
class StatValueConverterTest {

    private val converter = StatValueConverter()

    @Test
    fun testConvertIntegerValue() {
        val json = converter.toString(Stat.Value.Integer(4))
        val integerValue = converter.fromString(json)

        assertThat(json, `is`("""{"type":"integer","value":4}"""))
        assertThat(integerValue, instanceOf(Stat.Value.Integer::class.java))
        assertThat(
            (integerValue as Stat.Value.Integer).value,
            `is`(4)
        )
    }

    @Test
    fun testConvertTextValue() {
        val json = converter.toString(Stat.Value.Text("cool"))
        val textValue = converter.fromString(json)

        assertThat(json, `is`("""{"type":"text","value":"cool"}"""))
        assertThat(textValue, instanceOf(Stat.Value.Text::class.java))
        assertThat(
            (textValue as Stat.Value.Text).value,
            `is`("cool")
        )
    }

    @Test
    fun testConvertMultiOptionValue() {
        val json = converter.toString(Stat.Value.MultiOption(setOf("very", "cool")))
        val multiOptionValue = converter.fromString(json)

        assertThat(json, `is`("""{"type":"multi_option","values":["very","cool"]}"""))
        assertThat(multiOptionValue, instanceOf(Stat.Value.MultiOption::class.java))
        assertThat(
            (multiOptionValue as Stat.Value.MultiOption).values,
            `is`(setOf("very", "cool"))
        )
    }

    @Test
    fun testConvertMultiOptionRepeatValue() {
        val json = converter.toString(Stat.Value.MultiOptionRepeat(setOf(
            Stat.Value.MultiOptionRepeat.Option("very", 1),
            Stat.Value.MultiOptionRepeat.Option("cool", 2)
        )))
        val multiOptionRepeatValue = converter.fromString(json)

        assertThat(
            json,
            `is`("""{"type":"multi_option_repeat","values":[{"value":"very","repetitions":1},{"value":"cool","repetitions":2}]}""")
        )
        assertThat(multiOptionRepeatValue, instanceOf(Stat.Value.MultiOptionRepeat::class.java))
        assertThat(
            (multiOptionRepeatValue as Stat.Value.MultiOptionRepeat).values,
            `is`(setOf(
                Stat.Value.MultiOptionRepeat.Option("very", 1),
                Stat.Value.MultiOptionRepeat.Option("cool", 2)
            ))
        )
    }
}
