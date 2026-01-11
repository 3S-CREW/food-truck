import Sidebar from '@/components/layout/UserSideBar';
import * as S from '@/pages/UserHome/style';
import { colors } from '@toss/tds-colors';
import { IconButton, SearchField } from '@toss/tds-mobile';
import { useState } from 'react';

const UserHomePage = () => {
  const [isSidebarOpen, setIsSidebarOpen] = useState(false);

  // 추후 지도 API 로드 시 사용할 영역
  return (
    <S.Container>
      {/* 상단 영역 (메뉴 버튼 + 검색창) */}
      <S.TopFixedBar>
        <IconButton
          name='icon-line-three-mono'
          color={colors.grey700}
          aria-label='좌측 내 정보 탭'
          onClick={() => setIsSidebarOpen(true)}
          style={{ paddingInline: 0 }}
        />
        <S.SearchFieldWrapper>
          <SearchField
            placeholder='푸드트럭 검색...'
            style={{
              width: '100%',
              backgroundColor: 'transparent',
              margin: 0,
              padding: 0,
            }}
          />
        </S.SearchFieldWrapper>
      </S.TopFixedBar>

      {/* 지도 영역 (API 연동 전 플레이스홀더) */}
      <S.MapPlaceholder>
        {/* 더미 마커들 (API 연동 후에는 데이터로 렌더링) */}
        <S.MockMarker style={{ top: '40%', left: '40%' }}>
          <div className='marker-icon'>🚚</div>
          <div className='marker-label'>한식 트럭</div>
        </S.MockMarker>

        <S.MockMarker style={{ top: '45%', left: '60%' }}>
          <div className='marker-icon'>🚚</div>
          <div className='marker-label'>핫도그 마을</div>
        </S.MockMarker>

        <S.MockMarker style={{ top: '60%', left: '50%' }}>
          <div className='marker-icon'>🚚</div>
          <div className='marker-label'>타코야끼 천국</div>
        </S.MockMarker>
      </S.MapPlaceholder>

      {/* 하단 내 위치 버튼 */}
      <S.BottomFloatingButton>
        <IconButton
          name='icon-locator-mono'
          variant='fill'
          iconSize={24}
          color={colors.blue500}
          bgColor={colors.whiteOpacity900}
          style={{
            boxShadow: '0 4px 8px rgba(0,0,0,0.1)',
            borderRadius: '50%',
          }}
          aria-label='내 위치'
        />
      </S.BottomFloatingButton>

      {/* 사이드바 (Overlay) */}
      <Sidebar isOpen={isSidebarOpen} onClose={() => setIsSidebarOpen(false)} />
    </S.Container>
  );
};

export default UserHomePage;
