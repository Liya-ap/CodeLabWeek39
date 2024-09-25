package entities;

import dtos.PoemDTO;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data

public class PoemsList {
    private Set<PoemDTO> poemsDTOs = new HashSet<>();

    public PoemsList() {
        addAllPoems();
    }

    private void addAllPoems() {
        poemsDTOs.add(new PoemDTO(
                "How Great My Grief",
                Type.RONDEL,
                "How great my grief, my joys how few, Since first it was my fate to know thee! "
                        + "- Have the slow years not brought to view How great my grief, my joys how few, "
                        + "Nor memory shaped old times anew, Nor loving-kindness helped to show thee "
                        + "How great my grief, my joys how few, Since first it was my fate to know thee?",
                "Thomas Hardy"
        ));

        poemsDTOs.add(new PoemDTO(
                "From Second Satire",
                Type.TERZA_RIMA,
                "My mother’s maids, when they did sew and spin, They sang sometimes a song of the field mouse, "
                        + "That for because their livelihood was but so thin Would needs go seek her townish sister’s house. "
                        + "She thought herself endured too much pain: The stormy blasts her cave so sore did souse...",
                "Sir Thomas Wyatt"
        ));

        poemsDTOs.add(new PoemDTO(
                "Reduced Circumstances",
                Type.TERZA_RIMA,
                "He wasn’t always stretched that way, you know, strained through that fine sieve and powdered out "
                        + "into polite society, a mote in someone else’s eye. The guy trained hard, "
                        + "compressed himself into the various molds others thought he’d fit. Nobody bothered "
                        + "to show they cared–to try to add three days back into his week or put July back into his year–"
                        + "they just smiled, used him for their purposes, the last of which was as the subject of some brief "
                        + "but witty poem, and nobody knew or wished to know the worst, most violent effect: "
                        + "His circumstances were reduced until he merely sat with folded hands.",
                "Harvey Stanbrough"
        ));

        poemsDTOs.add(new PoemDTO(
                "Ye Goatherd Gods",
                Type.SESTINA,
                "Ye goatherd gods, that love the grassy mountains, Ye nymphs which haunt the springs in pleasant valleys, "
                        + "Ye satyrs joyed with free and quiet forests, Vouchsafe your silent ears to plaining music, "
                        + "Which to my woes gives still an early morning, And draws the dolor on till weary evening.",
                "Sir Philip Sidney"
        ));

        poemsDTOs.add(new PoemDTO(
                "Sestina of the Tramp-Royal",
                Type.SESTINA,
                "Speakin’ in general, I ’ave tried ’em all— The ’appy roads that take you o’er the world. "
                        + "Speakin’ in general, I ’ave found them good For such as cannot use one bed too long, "
                        + "But must get ’ence, the same as I ’ave done, An’ go observin’ matters till they die.",
                "Rudyard Kipling"
        ));

    }

    public void addPoemToList(PoemDTO poemDTO){
        poemsDTOs.add(poemDTO);
    }

}
