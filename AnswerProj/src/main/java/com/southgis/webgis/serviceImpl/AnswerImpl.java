package com.southgis.webgis.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.southgis.webgis.Response.ResponseInfo;
import com.southgis.webgis.Response.entity.EnumErrCode;
import com.southgis.webgis.entity.Question;
import com.southgis.webgis.entity.ScenicEntity;
import com.southgis.webgis.entity.Score;
import com.southgis.webgis.mapper.ScenicMapper;
import com.southgis.webgis.service.AnswerService;
import lombok.extern.slf4j.Slf4j;
import org.ansj.domain.Result;
import org.ansj.splitWord.analysis.NlpAnalysis;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

/**
 * 智能问答接口
 *
 * @author QingYang
 */
@Slf4j
@Service(AnswerImpl.SERVICE_BEAN_NAME)
public class AnswerImpl implements AnswerService {

    public final static String SERVICE_BEAN_NAME = "AnswerService";

    @Resource
    ScenicMapper scenicMapper;


    /**
     * 智能问答--传入问句
     *
     * @return
     */
    public ResponseInfo answerSentence(Question model) {
        //原来的分词
//        String quan = ToAnalysis.parse(sentence).toStringWithOutNature();
//        log.info("基本分词：" + quan);

        //log.info(model.toString());
        System.out.println(model.getQuesion());
        List<Score> scores = getImport(model.getQuesion());
        for (Score score1 : scores) {
            System.out.println(score1);
        }

        //查询地区景点及 筛选景点等级
        //QueryWrapper<ScenicEntity> qw = new QueryWrapper<>();
        List<ScenicEntity> scenicEntity = new ArrayList<>();
        List<ScenicEntity> diquEntity = new ArrayList<>();
        List<ScenicEntity> dengjiEntity = new ArrayList<>();
        for (int i = 0; i < scores.size(); i++) {
            QueryWrapper<ScenicEntity> qw = new QueryWrapper<>();
            qw.like("diqu", scores.get(i).key);
            diquEntity = scenicMapper.selectList(qw);
            if (!diquEntity.isEmpty()) {
                log.info(diquEntity.toString());
                break;
            }
        }
        if (!diquEntity.isEmpty()) {
            String nu = "null";
            for (ScenicEntity en : diquEntity) {
                if (!en.getDengji().isEmpty() && !en.getDengji().equals(nu)) {
                    dengjiEntity.add(en);
                }
            }
            log.info(dengjiEntity.toString());
        }
        if (dengjiEntity.isEmpty()) {
            scenicEntity = diquEntity;
        } else
            scenicEntity = dengjiEntity;

        return new ResponseInfo(EnumErrCode.OK, null, scenicEntity);
    }

    public ResponseInfo wordCloud() {

        String word = "\t北方有山名白石自古以来，世界的文明大多发源于河流两岸的平原，而文明的重要载体——文人墨客却钟爱于游历山川。打开中国的地形图，在黄土高原和华北平原之间有一条天然的分割线，这条在《愚公移山》中被形容“方七百里，高万仞”的地标就是今天的太行山脉。俯瞰巍巍太行自北向南纵跨京、冀、晋、豫四个省市，北起北京市西山，南接山西王屋山，绵延800余里，成就了北方山脉中的众多奇观，位于河北省涞源县城南的白石山便是其一。白石山世界地质公园白石山世界地质公园白石山世界地质公园白石山世界地质公园白石山世界地质公园白石山地质公园是世界地质公园、国家5A级景区，偶从电梯广告看到白石山被誉为“北方第一奇山”，是全国唯一的一处大理岩峰林地貌，奇、雄、险、幻、秀五大特点可媲美黄山，遂百思不得其解。因为在我的刻板印象中，北方的山因缺少水系的装点，比南方的山总少了那么点“秀气”，与五岳之一的北岳恒山比又少了那么点“名气”，从历代文人墨客点名的数量看，还似乎少了那么点“才气”，那为何此山可媲美黄山，“奇”又从何而来？带着这些疑问，约约皮在这个周末开启白石山两日自驾之旅。白石山世界地质公园白石山夏有青葱之趣，可谓避暑圣地，秋更有风情雅韵，当层峦叠嶂被金秋染透的时候，便是对秋最好的诠释。白石山世界地质公园白石山世界地质公园白石山世界地质公园“约”见白石山我是约约皮，旅行、文艺、轻生活，一个都不能少。如果你喜欢我的文字和摄影作品，欢迎随时交流。【拍摄器材】canon5d3，f2.816-35mm，手机iphone8【背景音乐】周杰伦等你下课白石山世界地质公园行程安排及游览路线：第一天：8:00-11:00北京城区出发自驾至白石山景区东门11:00-12:00东门乘车入住尚云间酒店12:00-18:00酒店-韭菜园索道-飞狐峡游览线（黄线）-飞云口索道-双雄石游览线（蓝线）-酒店第二天：清晨酒店早餐，感受白石山清晨，中午下山返京。白石山世界地质公园“源涞”如此三伏天的北京清晨，燥热扑面而来，如果不在空调屋里老实呆着，就一定会“湿身”。提前查好路线，买好门票，订好酒店，再备上点登山的干粮，别人羡慕的一儿一女一堆补习班的周末生活与我无关，全新的组合，和老爸两人一车，带上佛系“蛙儿子”就这样出发了。白石山世界地质公园蛙儿子第一次坐缆车白石山世界地质公园白石山距离涞源县城15公里，从北京城区出发首推六环-京昆高速-张石高速这条线，全程220公里，3个小时，虽是高速路况但隧道限速的情况比较多，走到张石高速看到涞源白石山出口再走一段就到了景区东门。抵达停车场下意识看了下表10点，天气多云，提前在网上买好了优惠门票，在窗口买了上山的大巴车票，从闸机刷身份证顺利进园。白石山世界地质公园白石山世界地质公园此时此刻，暂时告别车子里泡着枸杞的保温杯，尽情期待眼前云雾缭绕的诗意远方。13公里非典型长途“拔”涉，车身围绕山体缓慢盘旋上升至海拔1800米，耳鼓膜开始有点胀痛，身边游客的聊天声和发动机声渐渐变小，老爸多年不爬山晕晕乎乎问了句“是不是快降落了？”“您真是身未动，心飞扬了”。约摸20分钟，迈下巴士，刚刚山脚下仰望的云雾缭绕被峰林间透过的光线渐渐拨开，一抹醉人的高山草甸和红顶酒店画面展现在眼前，我和老爸互相对视，“到啦”，白石圣境，“源涞”如此！写意山水，独爱留白把随行物品放到酒店稍作调整，和老爸步行至韭菜园，做足了用双脚征服这座奇山的内心戏，脑中尽展诗仙诗圣诗鬼们题壁绝句，或胸怀壮志登碣石叹大好河山，或忧国忧民念天地独怆然泪下，无奈老爸耳顺之年体力有限，为了不降低旅行的舒适度，我们决定飞狐峡游览线韭菜园至望亭台一段，以及穿越飞云口一段乘坐缆车。文章有序言，古典音乐有序曲，似乎一切动人心弦的篇章向来都不是平铺直叙，姑且就把索道这5分钟的路程叫做渐入佳境。白石山世界地质公园白石山世界地质公园白石山盛产白色的大理石，岩石表面经过风化虽看不出大理石应有的色彩，但断裂处仍依稀可见温婉如玉的白石肌理，绵延在7000余米的山脊之间，与山体的黛色，嶙峋怪石，云海仙境构成一幅浑然天成的水墨画。白石山世界地质公园图：史前岩石白石山世界地质公园写意山水，国画中的极简主义，以浓淡干湿焦调五色，绘山水石林屋于生宣，辅丹青点四季神韵，借留白传画外余音。白石山有四绝：峰林、怪石、云海、栈道。白石山还有十二景：白石晴云、山盟海誓、太行之神、景心流岚、三圣朝佛、白石凇韵、姜公钓鱼、仙人晒靴、双雄守山、凌波微步、祥云佛光。黄线与蓝线恰把这峭壁之上的十二美景串联，太行万丈绝壁，游走其中如落仙境，薄雾飘渺，云海翻涌，行走如笔尖不断晕染水墨之韵，尽享白石山大气之美。白石山世界地质公园图↑：三洞天白石山世界地质公园又突然哼唱起Jay的青花瓷那几句。水墨丹青于瓷器之上的绝美组合，胎釉的温润与线条的婀娜如窈窕女子穿梭在水墨山间，成为一抹丹砂。白石山世界地质公园云中做个仙儿白石山世界地质公园天青色等烟雨，而我在等你炊烟袅袅升起，隔江千万里在瓶底书汉隶仿前朝的飘逸就当我，为遇见你伏笔白石山世界地质公园云中情人桥，若隐若现，这是通往太行之神的必经之路。白石山世界地质公园凌波微步，演绎高海拔之恋白石山堪称中国栈道第一山，因为景区内有两条高海拔栈道，位于海拔1900米的双雄石玻璃栈道和海拔1600米的飞狐双层玻璃廊桥。近几年国内的各个景区都开发玻璃栈道，但白石山这两条栈道占据了很多第一，双雄石玻璃栈道95米长，2米宽，是国内第一条海拔最高、最长、最宽的悬空玻璃栈道。飞狐双层玻璃廊桥，长200米，宽2米，是全国唯一一座双层玻璃桥。白石山世界地质公园";
        word += "\t京城时光掠影天坛天坛祈年殿北京北京北京慕田峪长城慕田峪长城北京慕田峪长城慕田峪长城故宫紫禁城北京北京北京北京故宫西北角楼北京颐和园北京北京北京颐和园十七孔桥北京人物闪亮登场1、摄影师+导航仪+钱袋=痘腐先生（爸比）慕田峪长城2、攻略师+账房+保姆=生梨小姐（妈咪）慕田峪长城3、少爷+替补摄影师=小小顾（6周岁学龄前儿童，爸比妈咪的孩纸）慕田峪长城我们仨慕田峪长城启程，又是一个八月盛夏的暑气或许是孩童一年中最快乐的时光夏天是冰激凌的季节，夏天是不上学的季节，夏天是戴上草帽像模像样旅行的季节。这个八月，我们仨当然也不愿宅在家里。旅行的目的地很多，而生梨想让小小顾先了解自家的土地，然后带着客观与从容走向远方。因此，今年的八月，让我们移师京城，领略那跨越600年的皇城风范。碎碎念的费用与日程大交通：魔都到帝都，今年8月新开的16节编组复兴号动车是生梨心心念念的交通工具。两座城市用时速可达350千米每小时的高速列车连接，只需4个半小时就能到达。G6号列车早上7点从上海火车站出发，11:38分到达北京南站G3号列车下午2点从北京南站出发，18:28分到达上海虹桥火车站速度满分，性价比满分！全程来回2600元三人（孩子120cm以上需要买半票）住宿：北京的市区景点集中在中轴线上，因此在选择酒店的时候我也是绕着天安门附近寻找，以4星酒店为底线，最终我订的是西城区的西单美爵酒店。这家酒店在二环内，距离西单和宣武门地铁站都只有400~500米的距离，公交车站仅100米的距离。距离北京南站仅20分钟地铁车程。距离天安门广场3公里，距离天坛公园5公里。节约可乘公交车、地铁；奢靡可滴滴打车（距离还都不远），可以说地理位置完美。提前2周订的房间，连住3天以上后4天的总价在3000元+，可以说也是性价比很高了！(毕竟在2环内天安门附近啊！)房间32平方,很神奇的是非常凉快，从头到尾我们都无需开空调，要知道这可是8月啊！痘腐先生表示，这可是二环内的房子啊，得多贵啊！所以才能如此凉快啊！（生梨晕倒....）北京西单美爵酒店酒店的圣旨范儿指南，有没有很cool门票：1.景山公园：成人10元/人;6周岁以上18岁以下半价；6周岁或以下或1.2米以下免费。2.颐和园联票：成人60元每人；6周岁以上18岁以下半价；6周岁或以下或1.2米以下免费。3.故宫门票：成人60元每人；6周岁以上18岁以下半价；6周岁或以下或1.2米以下免费。4.慕田峪长城门票：成人40元每人；6周岁以上18岁以下半价；6周岁或以下或1.2米以下免费。5.慕田峪长城缆车双程：成人120元每人；1.2-1.4米半价，1.4米以上全价。6.天坛公园联票：成人34元每人；6周岁以上18岁以下半价；6周岁或以下或1.2米以下免费。市区交通：1.到北京南站先去办一张交通卡，100元一张，内含80元储值+20元押金。2.北京的地铁与公交换乘时，公交车有半价优惠。3.乘坐北京公交车一定记得上下车都要刷卡，否则不仅没有优惠，而且会扣除最高票价！4.我们住在二环内，基本上在市区3-5公里的距离都是打车的，也都不是很堵。（墙裂怀疑五环之歌的真实性！）美食：1.五星吐血推荐：四季民福和局气两家网红餐厅。人均都在120元左右。2.疲劳的时候我们都是酒店直接点外卖，不用等位，送货超快！3.早餐我们还吃过味多美和稻香村，北京稻香村之后会详述哈。具体行程安排：（似乎过于简单了？？但是这个才适合孩子的节奏啊！）北京由于时值盛夏又带着6岁的小小顾，所以我们精选景点，避开所有12点到4点的暴晒时光，尽量一早去景点，也可以避开旅行团，欣赏游人较少时的风光。事实证明，非常正确！除此以外，在北京的5天有3天是下雨的，但是幸运的是，我们根据“墨迹天气”的精准预测，寻找到每天雨后少云的时光，去到了景山公园、天坛公园，看到了雨后的美景。所以，如果你也带着孩子出门，那么不妨也像我们一样，早睡早起，利用早晨与傍晚的时光，既能获得最佳摄影时光，又能玩得最尽心。前方高能预警：你一定要下载的神器APP因为前期的准备（尤其是马蜂窝各种攻略游记的阅览），这里给大家推荐几个一定要知道和下载app，不仅可以帮助大家在出游时节约时间。1.网红饭店排队神器：《美味不用等》我就是用这个神器直接在去饭店的路上手机排队四季民福、局气，到了就直接进店，省去大量排队时间。（好多人说排四季民福、局气等都用了超过2小时，而我们都是到了就基本进店吃咯！）大董、小大董、小吊梨汤，那家小馆等店都可以用这个app排队，超级棒！2.门票购买神器：畅游公园。通过这个神器，我在到达天坛公园、景山公园、颐和园前就已经有了电子票，大家只要出示二维码就可以直接进园了，无需再排队购票。北京就是上面这个二维码，大家扫一下就能去买门票啦。3.故宫博物院官网：http://www.dpm.org.cn/Home.html通过这个官网大家可以提前10天预订故宫的门票，同时可以在网站上看到故宫的地图，提前确定自己的路线以及正在展出的展览。当然，也可以通过故宫全景虚拟游览先行观察下。4.去故宫前必读书目《故宫院长说故宫》通过阅读这本由故宫博物院副院长写的书，你将与故宫有情感上的连接，同时作为一个妈妈，我觉得比起听导游或者解说器的解说，肯定宝宝更喜欢听妈妈去说，因此在旅行前我用了1周读完了这本并不好玩的书，但是它会让你去了解故宫的出现，明白故宫的美，懂得如何去欣赏故宫。";
        Result result = NlpAnalysis.parse(word);
        log.info(String.valueOf(result));

        HashMap<String, Integer> wordMap = new HashMap<>();
        for (int i = 0; i < result.getTerms().size(); i++) {
            String zi = result.getTerms().get(i).getName();
            if (!wordMap.containsKey(zi)) {
                wordMap.put(zi, 1);
            } else {
                int val = wordMap.get(zi);
                wordMap.put(zi, val + 1);
            }
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<>(wordMap.entrySet()); //将map的entryset放入list集合
        //对list进行排序，并通过Comparator传入自定义的排序规则
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        //用迭代器对list中的键值对元素进行遍历
        Iterator<Map.Entry<String, Integer>> iter = list.iterator();
        while (iter.hasNext()) {
            Map.Entry<String, Integer> item = iter.next();
            String key = item.getKey();
            int value = item.getValue();
            System.out.println("键" + key + "值" + value);
        }
        //log.info(String.valueOf(wordMap));
        return new ResponseInfo(EnumErrCode.OK, null, null);
    }

    /***
     * 连接es数据库参数
     */
//    public void init(){
//        this.IP = "192.168.？.？";
//        this.PORT = 9300;
//    }

    /**
     * TextRank算法--实现分词及问题关键词提取
     *
     * @param model
     * @return
     */
    public List<Score> getImport(String model) {

        List<String> keyWords = new ArrayList<>();
        int k = 2;  //窗口大小/2
        float d = 0.85f;

        /**
         * 标点符号、常用词、以及“名词、动词、形容词、副词之外的词”
         */
        Set<String> stopWordSet = new HashSet<String>();
        stopWordSet.add("是");
        stopWordSet.add("的");
        stopWordSet.add("地");
        stopWordSet.add("从");
        stopWordSet.add("将");
        stopWordSet.add("但");
        stopWordSet.add("都");
        stopWordSet.add("和");
        stopWordSet.add("为");
        stopWordSet.add("让");
        stopWordSet.add("在");
        stopWordSet.add("由");
        stopWordSet.add("上");
        //String field = "PageRank近似于一个用户，是指在Internet上随机地单击链接将会到达特定网页的可能性。通常，能够从更多地方到达的网页更为重要，因此具有更高的PageRank。每个到其他网页的链接，都增加了该网页的PageRank。具有较高PageRank的网页一般都是通过更多其他网页的链接而提高的。";


        Analyzer analyzer = new IKAnalyzer(true);
        TokenStream ts = null;
        //分词
        try {
            ts = analyzer.tokenStream("myfield", new StringReader(model));
            OffsetAttribute offset = (OffsetAttribute) ts.addAttribute(OffsetAttribute.class);
            CharTermAttribute term = (CharTermAttribute) ts.addAttribute(CharTermAttribute.class);
            TypeAttribute type = (TypeAttribute) ts.addAttribute(TypeAttribute.class);
            ts.reset();

            while (ts.incrementToken()) {
                if (!stopWordSet.contains(term.toString())) {
                    keyWords.add(term.toString());
                }
            }
            ts.end();
        } catch (IOException var14) {
            var14.printStackTrace();
        } finally {
            if (ts != null) {
                try {
                    ts.close();
                } catch (IOException var13) {
                    var13.printStackTrace();
                }
            }

        }

        Map<String, Set<String>> relationWords = new HashMap<>();

        //获取每个关键词 前后k个的组合
        for (int i = 0; i < keyWords.size(); i++) {
            String keyword = keyWords.get(i);
            Set<String> keySets = relationWords.get(keyword);
            if (keySets == null) {
                keySets = new HashSet<>();
                relationWords.put(keyword, keySets);
            }

            for (int j = i - k; j <= i + k; j++) {
                if (j < 0 || j >= keyWords.size() || j == i) {
                    continue;
                } else {
                    keySets.add(keyWords.get(j));
                }
            }
        }

        /* for (String s : relationWords.keySet()) {
            System.out.print(s+" ");
            for (String s1 : relationWords.get(s)) {
                System.out.print(s1+" ");
            }
            System.out.println();
        }*/


        Map<String, Float> score = new HashMap<>();
        float min_diff = 0.1f; //差值最小
        int max_iter = 100;//最大迭代次数

        //迭代
        for (int i = 0; i < max_iter; i++) {
            Map<String, Float> m = new HashMap<>();
            float max_diff = 0;
            for (String key : relationWords.keySet()) {
                Set<String> value = relationWords.get(key);
                //先给每个关键词一个默认rank值
                m.put(key, 1 - d);
                //一个关键词的TextRank由其它成员投票出来
                for (String other : value) {
                    int size = relationWords.get(other).size();
                    if (key.equals(other) || size == 0) {
                        continue;
                    } else {
                        m.put(key, m.get(key) + d / size * (score.get(other) == null ? 0 : score.get(other)));
                    }
                }
//                System.out.println("m.get(key):"+m.get(key)+" score:"+(score.get(key) == null ? 0 : score.get(key)));
                max_diff = Math.max(max_diff, Math.abs(m.get(key) - (score.get(key) == null ? 0 : score.get(key))));
            }

            score = m;
            if (max_diff <= min_diff) {
                System.out.println("迭代次数：" + i);
                break;
            }
        }

        List<Score> scores = new ArrayList<>();
        for (String s : score.keySet()) {
            Score score1 = new Score();
            score1.key = s;
            score1.significance = score.get(s);
            scores.add(score1);
        }

        scores.sort(new Comparator<Score>() {
            @Override
            public int compare(Score o1, Score o2) {
                if (o2.significance - o1.significance > 0) {
                    return 1;
                } else {
                    return -1;
                }

            }
        });
        return scores;
    }
}