package kr.anima.xd.s.calendardiarytest.utils;

/**
 * Created by alfo6-10 on 8/23/2017.
 */

public class DatabaseContents {

    //FORMAT
    public static final String FORMAT_MONTH= "%02d";
    public static final String FORMAT_DATE="yyyyMMdd";
    public static final String FORMAT_DATE_TIME="yyyyMMdd hh:mm";
    public static final String FORMAT_TIME="hh:mm";

    // COLUMN NAME
    public static final String COL_TITLE="Title"; //제목
    public static final String COL_DESCRIPTION="Description"; //설명
    public static final String COL_START="Start Date"; //시작일
    public static final String COL_END="End Date"; // 종료일
    public static final String COL_ALLDAY="All Day"; // 하루종일
    public static final String COL_DURATION="Duration"; // 기간
    public static final String COL_RRULE="Repeat Rule"; // 반복타입
    public static final String COL_RDATE="Repeat Date"; // 반복날짜
    public static final String COL_EXRULE="Exception Rule"; // 반복예외
    public static final String COL_EXDATE="Exception Date"; // 반복예외 날짜
    public static final String COL_ALARM="Alarm"; // 알람여부
    public static final String COL_STATUS="Status"; // 상태
    public static final String COL_DELETED="Deleted"; // 삭제여부
    public static final String COL_RANK="Rank"; // 우선순위도
    public static final String COL_TRACKER="Tracker"; // 기록표

    // ELEMENT
    public static final String ELEMENT_APPEARANCE="Appearance"; //외모
    public static final String ELEMENT_BUSINESS="Business"; //사업
    public static final String ELEMENT_EDUCATION="Education"; //교육
    public static final String ELEMENT_EMOTION="Emotion"; //감정
    public static final String ELEMENT_ENVIRONMENT="Environment"; //환경
    public static final String ELEMENT_FINANCES="Finances"; //재정
    public static final String ELEMENT_HEALTH="Health"; //건강
    public static final String ELEMENT_RELATIONSHIP="Relationship"; //관계
    public static final String ELEMENT_SPIRITUALITY="Spirituality"; //정신

    // STATUS TYPE
    public static int STATUS_TENTATIVE=2; //미확정
    public static int STATUS_CONFIRMED=0; //확정, 진행중
    public static int STATUS_COMPLETED=1; // 완료
    public static int STATUS_CANCELED=-1; // 취소

    // STATUS DATABASE
    public static final String INSERT_SUCCESSFUL="Save Successful";
    public static final String INSERT_FAIL="Save Fail";
    public static final String DELETED_SUCCESSFUL="Deleted Successful";
    public static final String DELETED_FAIL="Deleted fail";
    public static final String MODIFY_SUCCESSFUL="Modify Successful";
    public static final String MODIFY_FAIL="Modify Fail";

}
